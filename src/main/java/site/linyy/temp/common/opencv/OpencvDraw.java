package site.linyy.temp.common.opencv;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * opencv 画图
 *
 */
public class OpencvDraw{
	
	public static void main(String[]args) {
		
		for(int i=3;i<30;i++) {
			polyPieces("f:\\temp\\"+i+".jpg",i);
		}
	}
	
	/**
	 * 绘制所有像素点
	 * 图很难看
	 */
	public static void drawAllPixel(String path,String targetPath) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 
        Size size = new Size( 256*16,256*16); //图像像素高度不能太大，10000<max<100000，具体数值未测
        Mat image = new Mat(size,CvType.CV_8UC3); //mat构造函数，构造完后需要放入像素点才行
        OpencvBase.putPixel(image,size); //初始化像素值
        for(int i=0;i<256;i++) {
        	for(int j=0;j<256;j++) {
        		for(int k=0;k<256;k++) {
        			double[] d = {k,j,i};
        				int width = k+j*256;
        				int realWidth = width%(256*16);
        				int realHeight = i*16+width/(256*16);
        				image.put(realHeight, realWidth, d);
        		}
        	}
        }
        Imgcodecs.imwrite(targetPath, image);
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
        return;
	}

	/**
	 * 绘制多边形
	 */
	public static void polyPieces(String targetPath,int step) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 
        Size size = new Size( 1366,768); //图像像素高度不能太大，10000<max<100000，具体数值未测
        Mat image = new Mat(size,CvType.CV_8UC3); //mat构造函数，构造完后需要放入像素点才行
        OpencvBase.putPixel(image,size); //初始化像素值
        List<RgbRange> rgbList = new ArrayList<>();
        setRgbList(rgbList); //颜色区间
        List<Integer> edgeList = new ArrayList<>();
        setEdgeList(edgeList);//多边形边数集合
//        IntPoint[][] basePoints = new IntPoint[(int) (size.height/step)][(int)(size.width/step)]; //有序
//        setBasePoints(basePoints,(int)(size.height/step),(int)(size.width/step),step);
        List<IntPointBean> basePoints = new ArrayList<>();
        setBasePointsList(basePoints,(int)(size.height/step),(int)(size.width/step),step);
       while(basePoints.size()>0) {
    	   drawRandom(image,basePoints,rgbList,edgeList,step); //随机点，随机颜色，随机多边形，删除画完的点
       }
        Imgcodecs.imwrite(targetPath, image);
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
        return;
	}

	/**
	 * 随机点，随机颜色，随机多边形
	 */
	private static void drawRandom(Mat image,List<IntPointBean> basePoints, List<RgbRange> rgbList, List<Integer> edgeList,int step) {
		
		int size = basePoints.size();
		int random  = (int)Math.random()*size;
		IntPointBean point = basePoints.get(random);
		basePoints.remove(random);
		int drawRange = 3; //在n倍step内画多边形
		List<Point> pointList = new ArrayList<>();
		randomPointList(point,drawRange*step,pointList,edgeList);
		Scalar color = randomColor(rgbList); //随机颜色
		Point[] points = new Point[pointList.size()];
		OpencvBase.drawPoly(image, color, pointList.toArray(points));
	}

	/**
	 * 生成随机颜色
	 */
	private static Scalar randomColor(List<RgbRange> rgbList) {

		int randomIndex = (int)(Math.random()*rgbList.size());
		RgbRange rgbRange = rgbList.get(randomIndex);
		return rgbRange.random();
	}

	/**
	 * 随机生成多边形顶点
	 */
	private static void randomPointList(IntPointBean point,int range,List<Point> pointList, List<Integer> edgeList) {

		//生成point点周围的顶点(上下左右平移最大距离：range）
		int edgeNum  = randomEdgeNum(edgeList);
		for(int i=0;i<edgeNum;i++) {
			int x =(int)( Math.random()*range*2-range)+point.getX();
			int y = (int)(Math.random()*range*2-range)+point.getY();
			Point p = new Point(x, y);
			pointList.add(p);
		}
	}

	/**
	 * 随机生成边数
	 */
	private static int randomEdgeNum(List<Integer> edgeList) {

		int index = (int)(Math.random()*edgeList.size());
		return edgeList.get(index);
	}

	/**
	 * 设置基础点
	 */
	private static void setBasePointsList(List<IntPointBean> basePoints, int x, int y, int step) {

		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				basePoints.add( new IntPointBean(j*step,i*step));
			}
		}
	}

	/**
	 * 设置基础点
	 * @param x 行数
	 * @param y 列数
	 */
	private static void setBasePoints(IntPointBean[][] basePoints,int x,int y,int step) {
		
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				basePoints[i][j] = new IntPointBean(i*step,j*step);
			}
		}
	}

	/**
	 * 设置多边形的边数
	 */
	private static void setEdgeList(List<Integer> edgeList) {

		edgeList.add(3);
		edgeList.add(4);
		edgeList.add(5);
		edgeList.add(6);
		edgeList.add(7);
	}

	/**
	 * 设置rgb列表
	 */
	private static void setRgbList(List<RgbRange> rgbList) {

		RgbRange range1 = new RgbRange(180,255,0,50,180,255);
		RgbRange range2= new RgbRange(0,50,60,120,180,255);
		RgbRange range3 = new RgbRange(0,68,200,255,188,243);
		RgbRange range4 = new RgbRange(23,77,200,255,0,50);
		RgbRange range5 = new RgbRange(202,248,210,255,0,50);
		RgbRange range6 = new RgbRange(207,255,168,215,0,50);
		RgbRange range7 = new RgbRange(200,255,40,95,0,50);
//		RgbRange range8 = new RgbRange(0,50,200,240,200,255);
		rgbList.add(range1);
		rgbList.add(range2);
		rgbList.add(range3);
		rgbList.add(range4);
		rgbList.add(range5);
		rgbList.add(range6);
		rgbList.add(range7);
//		rgbList.add(range8);
	}
	
}