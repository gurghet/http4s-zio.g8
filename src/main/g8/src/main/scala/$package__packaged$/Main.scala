package $package$

import scalaz.zio._
import scalaz.zio.console._
import scalaz.zio.interop.catz._
import scalaz.zio.interop.catz.implicits._
import ciris.refined._

object Main extends App {
  override def run(args: List[String]): ZIO[Environment,Nothing,Int] =
    (for {
      config <- Config.configF.result.absolve
      program <-  ZIO.runtime[Environment].flatMap { implicit rt =>
                    $name;format="Camel"$Server.streamOn[Task](config.port).compile.drain
                  }
    } yield program)
    .foldM(failure => putStrLn(s"Abort with error: $failure") *> ZIO.succeed(1), _ => ZIO.succeed(0))
}