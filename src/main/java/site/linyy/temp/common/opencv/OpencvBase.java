package site.linyy.temp.common.opencv;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/**oepncv基础。
 * @author Administrator
 *
 */
public class OpencvBase{
	
	//CvType （8uc3的常量类）
	
	public static void main(String[]args) {
		
		polygonExample("f:\\temp\\260397-14061015153679.jpg","f:\\temp\\a.jpg");
	}

	/**
	 * 绘制多边形例子
	 */
	public static void polygonExample(String path,String targetPath) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Point p1 = new Point();
		p1.x = 1;
		p1.y = 2;
		Point p2 = new Point();
		p2.x = 10;
		p2.y = 20;
		Point p3 = new Point();
		p3.x = 20000;
		p3.y = 2;
		 
        Size size = new Size( 256*16,256*16); //图像像素高度不能太大，10000<max<100000，具体数值未测
        Mat image = new Mat(size,CvType.CV_8UC3); //mat构造函数，构造完后需要放入像素点才行
        putPixel(image,size); //初始化像素值
        Scalar color = new Scalar(0, 0, 255);//颜色
        drawPoly(image,color,p1,p2,p3);//画多边形
        Imgcodecs.imwrite(targetPath, image);
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
        return;
	}


	/**画多边形
	 */
	public static void drawPoly(Mat image, Scalar color, Point ...p) {

		List<MatOfPoint> list = new ArrayList<>();
        MatOfPoint matOfPoint = new MatOfPoint(p);
        list.add(matOfPoint);
        Imgproc.fillPoly(image, list, color);
	}

	/** 初始化像素值设置为全黑的不带阿尔法通道的点
	 */
	public static void putPixel(Mat image, Size size) {

		byte[] bytes = new byte[3];
        for(int i=0;i<bytes.length;i++) {
        	bytes[i] = (byte) 0;
        }
        for(int i=0;i<size.height;i++) {
        	for(int j=0;j<size.width;j++) {
        			image.put(i,j, bytes);
        	}
        }
	}
	
}