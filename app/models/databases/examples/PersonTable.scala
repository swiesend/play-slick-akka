package models.databases.examples

// Importing the slick driver
import play.api.db.slick.Config.driver.simple._
//import scala.slick.driver.MySQLDriver.simple._
//import scala.slick.driver.H2Driver.simple._

// Importing data types (SQL type -> Scala type) 
import com.github.tototoshi.slick.JdbcJodaSupport._
import org.joda.time.DateTime
import java.sql.Date

// The case class to represent the data object
case class Person(id: Long, firstName: String, lastName: String, birthDay: org.joda.time.DateTime, year: java.sql.Date, age: Int)

/* 
 * The static object that does the actual work with variable table naming - note the names of tables and fields in H2 are case sensitive and must be all caps
 */
class PersonTable(tag: Tag, tableName: String) extends Table[Person](tag, tableName) {

  def id = column[Long]("id", O.PrimaryKey)
  def firstName = column[String]("firstname", O.NotNull)
  def lastName = column[String]("lastname", O.NotNull)
  def birthDay = column[DateTime]("birthday", O.NotNull)
  def year = column[Date]("year", O.NotNull)
  def age = column[Int]("age", O.NotNull)

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, firstName, lastName, birthDay, year, age) <> (Person.tupled, Person.unapply _)
}