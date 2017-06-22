import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.nutz.lang.Streams;

import com.mulhyac.zimg.client.ZimgClient;
import com.mulhyac.zimg.client.bean.ZimgFile;

public class ZimgTest {

	public static void main(String[] args) throws FileNotFoundException {
		ZimgClient zc = new ZimgClient("http://192.168.2.199:4869");
		ZimgFile zimgOne = zc.upload(new File("D:\\tmp\\1.jpg"));
		InputStream isOne = Streams.fileIn(new File("D:\\tmp\\2.png"));
		ZimgFile zimgTwo = new ZimgClient("http://192.168.2.199:4869").upload(isOne);
		InputStream isTwo = new FileInputStream(new File("D:\\tmp\\3.gif"));
		ZimgFile zimgThree = new ZimgClient("http://192.168.2.199:4869").upload(isTwo);
		System.out.println(zimgOne.getZimgPath());
		System.out.println(zimgTwo.getZimgPath());
		System.out.println(zimgThree.getZimgPath());
	}

}
