package site.linyy.temp.common.opencv;

//2018.11.29 蚂蚁金服笔试题
// package whatever; // don't place package name!
//"hello ant financial world" --> "world finnacial ant hello"
//做一个句子单词倒转，要求所用临时空间不超过4个字符。

public class Test{

    public static void main(String[] args){
        
        System.out.println(reverse(new StringBuffer("hello ant financial world")));
    }
    
    private static String reverse(StringBuffer source){
        
        byte index = (byte)0;
        byte wordIndex = (byte)0;
        byte spaceIndex = (byte)(source.length() ); //空格索引
        
        while(index < source.length()){//"ello ant financial worldh"
            char first = source.charAt(0);
            if(first == ' '){// 
                source.deleteCharAt(0).insert(source.length() - wordIndex ,first); // 
                spaceIndex = (byte)(source.length() - wordIndex - 1);//
            }else{//"ello ant financial worldh"
                source.deleteCharAt(0).insert(spaceIndex-1,first);
            }
            wordIndex ++;//1
            index ++;//1
        }
        return source.toString();
    }

}