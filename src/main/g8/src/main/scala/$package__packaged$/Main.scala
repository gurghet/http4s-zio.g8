package $package$

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import scalaz.zio.interop.catz._

object Main extends IOApp {
  def run(args: List[String]) =
    $name;format="Camel"$Server.stream[IO].compile.drain.as(ExitCode.Success)
}