package controllers

import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ComputingControllerSpec extends PlaySpec {

  def controller = new ComputingController(stubControllerComponents())

  "get" should {
    "クエリーパラメータが2つある場合は、計算結果をレスポンスとして返す" in {
      val a = 1
      val b = 2
      val result = controller.plus(Some(a), Some(b))(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === "3")
    }

    """クエリーパラメータが2つもない場合は「Please give arguments of a and b.」というレスポンスを返す""" in {
      val a = 1
      val result = controller.plus(Some(a), None)(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === """Please give arguments of a and b.""")
    }

    """クエリーパラメータが3つ以上ある場合は、初めの2つを引数として計算する""" in {
      val a = 1
      val b = 2
      val c = 3
      val result = controller.plus(Some(a), Some(b))(FakeRequest())
      assert(status(result) === 200)
      assert(contentAsString(result) === "3")
    }
  }
}
