
public class CombineTS {
    public static void main(String[] args) {
        // ffmpeg -i "concat:001.ts|002.ts|003.ts|004.ts" -acodec copy -vcodec copy
        // -absf aac_adtstoasc out.mp4
        int len = 325;
        int numZero = String.valueOf(len).length();
        numZero = 6;
        StringBuffer sb = new StringBuffer();
        sb.append("ffmpeg -i \"concat: ");
        String line = "%0" + numZero + "d.ts|";
        for (int i = 0; i < len; i++) {
            sb.append(String.format(line, i));
        }
        sb.append(String.format("%0" + numZero + "d.ts", len));
        sb.append(" \" -acodec copy -vcodec copy -absf aac_adtstoasc combineVideo.mp4");
        System.out.println(sb.toString());
    }

}