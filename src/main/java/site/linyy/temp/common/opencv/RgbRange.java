package site.linyy.temp.common.opencv;

import org.opencv.core.Scalar;

/**
 * rgb颜色范围
 */
public class RgbRange{
	
	int rMin;
	int rMax;
	int gMin;
	int gMax;
	int bMin;
	int bMax;
	
	public RgbRange(int rMin,int rMax,int gMin,int gMax,int bMin,int bMax) {
		this.rMin = rMin;
		this.rMax = rMax;
		this.gMin = gMin;
		this.gMax=gMax;
		this.bMin = bMin;
		this.bMax = bMax;
	}
	
	/**
	 * 返回随机颜色
	 */
	public Scalar random() {
		
		int rRange = rMax-rMin+1;
		int rRandom  = (int)(Math.random()*rRange)+rMin;
		int gRange = gMax-gMin+1;
		int gRandom = (int)(Math.random()*gRange)+gMin;
		int bRange = bMax-bMin+1;
		int bRandom = (int)(Math.random()*bRange)+bMin;
		Scalar color = new Scalar(bRandom,gRandom,rRandom);
		return color;
	}
	
	public int getrMin() {
		return rMin;
	}
	public void setrMin(int rMin) {
		this.rMin = rMin;
	}
	public int getrMax() {
		return rMax;
	}
	public void setrMax(int rMax) {
		this.rMax = rMax;
	}
	public int getgMin() {
		return gMin;
	}
	public void setgMin(int gMin) {
		this.gMin = gMin;
	}
	public int getgMax() {
		return gMax;
	}
	public void setgMax(int gMax) {
		this.gMax = gMax;
	}
	public int getbMin() {
		return bMin;
	}
	public void setbMin(int bMin) {
		this.bMin = bMin;
	}
	public int getbMax() {
		return bMax;
	}
	public void setbMax(int bMax) {
		this.bMax = bMax;
	}
	
}