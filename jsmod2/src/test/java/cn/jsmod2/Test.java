package cn.jsmod2;




public class Test {

    public static void main(String[] args) throws Exception{

        String str ="\u001B[34m[\u001B[34m748]\u001B[36m[c.j.DefaultServer]\t\u001B[35m10:36:38\u001B[\u001BINFO\u001B\t]\u001B Server's folder/home/fafa/start.py/start.py";
        String[] strs = str.split("\\u001B");
        for(String s:strs){
            System.out.print(s.replaceAll("(\\[[0-9]+m)","<span style=''>"+s.replaceAll("\\[[0-9]+m","")+"</span>"));
        }
    }
}
