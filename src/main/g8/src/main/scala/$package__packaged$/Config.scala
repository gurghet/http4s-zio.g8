package $package$

import eu.timepit.refined.types.net.UserPortNumber
import ciris.{ loadConfig, ConfigResult, envF }
import scalaz.zio.Task
import ciris.cats.effect._
import scalaz.zio.interop.catz._
import ciris.refined._

final case class Config(
  port: UserPortNumber
)

object Config {
  val configF: ConfigResult[Task, Config] =
    loadConfig(
      envF[Task, UserPortNumber]("PORT")
    ) { portNumber =>
      Config(port = portNumber)
    }
}
