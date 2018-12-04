package site.linyy.temp.controller;

import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Controller;

@Controller
public class OpencvController {

    public static void main(String[] args) {

    	for(int i=1;i<200;i++) {
    		String istr=null;
    		if(i<10) {
    			istr="00"+i;
    		}else if(i<100) {
    			istr="0"+i;
    		}else {
    			istr=""+i;
    		}
    		jpgLine("f:\\temp\\0.jpg", i, "f:\\temp\\"+istr+".jpg");
    	}
        System.out.println("ok!");
    }

    /**提取颜色特殊的部分.
     * 适合图片颜色比较单一，线条颜色非常不同，效果比较好。
     * @param path 文件路径
     * @param cha 差值（点颜色与文档平均颜色差值）
     * @create: 2018年10月18日 下午5:39:23 linyy
     * @history:
     */
    public static void pngLine(String path, double cha, String target) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 读取原始图片
        org.opencv.core.Mat image = org.opencv.imgcodecs.Imgcodecs.imread(path,
            -1);
        double r = 0;
        double g = 0;
        double b = 0;
        int heightMax = image.height();
        int widthMax = image.width();
        for (int height = 0; height < heightMax; height++) {
            for (int width = 0; width < widthMax; width++) {
                double[] d = image.get(height, width);
                r += d[0];
                g += d[1];
                b += d[2];
            }
        }
        double ra = r / heightMax / widthMax;
        double ga = g / heightMax / widthMax;
        double ba = b / heightMax / widthMax;

        for (int height = 0; height < heightMax; height++) {
            for (int width = 0; width < widthMax; width++) {
                double[] d = image.get(height, width);
                if (Math.abs(ra - d[0]) > cha || Math.abs(ga - d[1]) > cha
                        || Math.abs(ba - d[2]) > cha) {
                } else {
                    d[0] = 0;
                    d[1] = 0;
                    d[2] = 0;
                    d[3] = 0;
                }
                image.put(height, width, d);
            }
        }
        Imgcodecs.imwrite(target, image);
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }

        return;
    }

    /**提取颜色特殊的部分.
     * 适合图片颜色比较单一，线条颜色非常不同，效果比较好。
     * @param path 文件路径
     * @param cha 差值（点颜色与文档平均颜色差值）
     * @create: 2018年10月18日 下午5:39:23 linyy
     * @history:
     */
    public static void jpgLine(String path, double cha, String target) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 读取原始图片
        org.opencv.core.Mat image = org.opencv.imgcodecs.Imgcodecs.imread(path);
        double r = 0;
        double g = 0;
        double b = 0;
        int heightMax = image.height();
        int widthMax = image.width();
        for (int height = 0; height < heightMax; height++) {
            for (int width = 0; width < widthMax; width++) {
                double[] d = image.get(height, width);
                r += d[0];
                g += d[1];
                b += d[2];
            }
        }
        double ra = r / heightMax / widthMax;
        double ga = g / heightMax / widthMax;
        double ba = b / heightMax / widthMax;

        for (int height = 0; height < heightMax; height++) {
            for (int width = 0; width < widthMax; width++) {
                double[] d = image.get(height, width);
                if (Math.abs(ra - d[0]) > cha || Math.abs(ga - d[1]) > cha
                        || Math.abs(ba - d[2]) > cha) {

                } else {
                    d[0] = 0;
                    d[1] = 0;
                    d[2] = 0;
                }
                image.put(height, width, d);
            }
        }
        Imgcodecs.imwrite(target, image);
    }

}
