import scala.io.StdIn._

object DummyMain extends App {

    import java.io.File
    import javax.imageio.ImageIO
    import java.awt.image.BufferedImage

    def crossTheImage(img: BufferedImage): BufferedImage = {
        // obtain width and height of image
        val w = img.getWidth
        val h = img.getHeight

        // create new image of the same size
        val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

        // copy pixels (mirror horizontally)
        for (x <- 0 until w)
            for (y <- 0 until h)
                out.setRGB(x, y, img.getRGB(w - x - 1, y) & 0xffffff)

        // draw red diagonal line
        for (x <- 0 until (h min w))
            out.setRGB(x, x, 0xff0000)

        out
    }

    def test() {
        println("Enter input photo: ")
        val path1 = readLine()
        // read original image, and obtain width and height
        val photo1 = ImageIO.read(new File(path1))

        val photo2 = crossTheImage(photo1)

        // save image to file "test.jpg"
        ImageIO.write(photo2, "jpeg", new File("test.jpeg"))
    }

    test()
}
