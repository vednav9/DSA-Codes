public class linear_string {
    public static void main(String[] args) {
        String name = "Vedant";
        char t='n';
        boolean ans=linerasearch(name,t);
        System.out.println(ans);
    }

    static boolean linerasearch(String name, char t){

        if(name.length()==0){
            return false;
        }
        for(int i=0;i<=name.length();i++){
            if(name.charAt(i)==t){
                return true;
            }
        }

        return false;
    }
}
