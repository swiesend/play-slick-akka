package databases.examples

import scala.slick.lifted.TableQuery

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import play.api.db.slick.Config.driver.simple._
import play.api.test.WithApplication
import scala.slick.jdbc.meta.MTable

import org.joda.time.DateTime

import java.sql.Date

import org.joda.time.LocalDate

import java.util.Calendar
import java.text.CalendarBuilder
import java.util.GregorianCalendar

import org.joda.time.DateTimeUtils


class PersonTest extends FlatSpec with Matchers {

  "Persons" should "have an age" in {

    new WithApplication {
      val db = play.api.db.slick.DB("default")

      val PersonsTableName = "persons"
      val persons = TableQuery[PersonTable]((tag: Tag) => new PersonTable(tag, PersonsTableName))

      db.withSession { implicit session =>
        if (MTable.getTables(PersonsTableName).list(session).isEmpty) {
          persons.ddl.create
        } else {
          persons.ddl.drop
          persons.ddl.create
        }

        val Now = DateTime.now
        val BirthDayString = "1969-01-01"
        val BirthDayArray = BirthDayString.split('-')
        val calendar = new GregorianCalendar()
        calendar.set(BirthDayArray(0).toInt, BirthDayArray(1).toInt, BirthDayArray(2).toInt)
        val Year = calendar.get(Calendar.YEAR)
        println(Year)
        var BirthDayTmp = Now
        if (Year >= 1970) {
          BirthDayTmp = DateTime.parse(BirthDayString)
        }
        val BirthDay = BirthDayTmp
        val SQLYear = java.sql.Date.valueOf(BirthDayString)
        val Age = Now.getYear() - Year
        println(Person(0, "firstname", "lastname", BirthDay, SQLYear, Age))

        val Amount = 10
        for (id <- 1 to Amount) {
          persons += Person(id, "firstname", "lastname", BirthDay, SQLYear, Age)
        }

        val tableLength: Int = persons.length.run
        assert(tableLength == Amount)

        if (!MTable.getTables(PersonsTableName).list(session).isEmpty) {
          persons.ddl.drop
        }
      }
    }
  }

}