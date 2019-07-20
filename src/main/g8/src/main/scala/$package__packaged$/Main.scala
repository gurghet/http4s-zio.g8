package $package$

import $package$.configuration.Configuration
import zio._
import zio.console._
import zio.interop.catz._
import zio.interop.catz.implicits._

object Main extends App {
  override def run(args: List[String]): ZIO[Environment,Nothing,Int] =
    (for {
      config <- configuration.load.provide(Configuration.Live)
      program <-  ZIO.runtime[Environment].flatMap { implicit rt =>
                    $name;format="Camel"$Server.streamOn[Task](config.port).compile.drain
                  }
    } yield program)
    .foldM(failure => putStrLn(s"Abort with error: \$failure") *> ZIO.succeed(1), _ => ZIO.succeed(0))
}
