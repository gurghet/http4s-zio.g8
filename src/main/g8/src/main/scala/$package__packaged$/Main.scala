package $package$

import cats.implicits._
import scalaz.zio.interop.catz._
import scalaz.zio.interop.catz.implicits._
import scalaz.zio.App
import scalaz.zio.ZIO
import scalaz.zio.Task

object Main extends App {
  override def run(args: List[String]): ZIO[Environment,Nothing,Int] =
    ZIO.runtime[Environment].flatMap { implicit rt =>
      $name;format="Camel"$Server.stream[Task].compile.drain.fold(_ => 1, _ => 0)
    }
}