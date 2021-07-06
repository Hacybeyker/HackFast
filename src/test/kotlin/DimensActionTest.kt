import action.dimens.DimensAction
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DimensActionTest {

    lateinit var dimensAction: DimensAction

    @Before
    fun setup() {
        dimensAction = DimensAction()
    }

    @Test
    fun `given a text without integers when passing through the isAnNumber method then return false`() {
        val status = dimensAction.isAnNumber("abc")
        Assert.assertEquals(false, status)
    }

    @Test
    fun `given a text with letters and numbers when it goes through the isAnNumber method then return false`() {
        val status = dimensAction.isAnNumber("a1b2c3")
        Assert.assertEquals(false, status)
    }

    @Test
    fun `given a text with only numbers when it goes through the isAnNumber method then return true`() {
        val status = dimensAction.isAnNumber("123")
        Assert.assertEquals(true, status)
    }

    @Test
    fun `given a text with a single number when it passes through the isAnNumber method then return true`() {
        val status = dimensAction.isAnNumber("1")
        Assert.assertEquals(true, status)
    }
}