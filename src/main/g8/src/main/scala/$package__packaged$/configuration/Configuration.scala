package $package$
package configuration

import ciris.cats.effect._
import ciris.refined._
import ciris.{ConfigErrors, ConfigResult, envF, loadConfig}
import eu.timepit.refined.types.net.UserPortNumber
import scalaz.zio.interop.catz._
import scalaz.zio.{Task, TaskR}
import cats.implicits._

final case class ServerConfig(
  port: UserPortNumber
)

trait Configuration extends Serializable {
  val configuration: Configuration.Service[Any]
}

object Configuration {
  trait Service[R] extends Serializable {
    def load: TaskR[R, ServerConfig]
  }

  trait Live extends Configuration {
    override val configuration: Service[Any] = new Service[Any] {
      val configF: ConfigResult[Task, ServerConfig] =
        loadConfig(
          envF[Task, UserPortNumber]("PORT")
            .orValue(UserPortNumber(8080))
        ) { portNumber =>
          ServerConfig(port = portNumber)
        }
      override def load: TaskR[Any, ServerConfig] =
        configF.result.map(leftConfigErrors2Exception).absolve
    }
  }

  def leftConfigErrors2Exception[A](eitherConfigErrors: Either[ConfigErrors, A]): Either[RuntimeException, A] =
    eitherConfigErrors.leftMap(configErrors => new RuntimeException(configErrors.toString))

  object Live extends Live
}