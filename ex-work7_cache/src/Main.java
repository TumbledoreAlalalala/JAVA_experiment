import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int tempSize=in.nextInt();
        int operateTimes=in.nextInt();
        Cache cache=new Cache(tempSize);
        for(int i=0;i<operateTimes;++i){
            String operate=in.next().toLowerCase();
            if(operate.startsWith("put")){
                int tempIndex=-1;
                int commaIndex=-1;
                operate=in.nextLine();
                if(operate.charAt(operate.length()-1)!=';')
                    operate+=";";
                while(operate.charAt(0)==' ')
                    operate=operate.substring(1);
                for(int j=0;j<operate.length();++j){
                    //逐个字符跳过 直到识别出完整的单个put指令
                    if(operate.charAt(j)!=';'){
                        if(tempIndex==-1)
                            tempIndex=j;
                        if(operate.charAt(j)==',')
                            commaIndex=j;
                    }
                    else{
                        Element newElement=new Element(operate.substring(tempIndex,commaIndex),operate.substring(commaIndex+1,j));
                        cache.put(newElement);
                        tempIndex=-1;
                    }
                }
            }
            else if(operate.startsWith("get")){
                operate=in.nextLine();
                while(operate.charAt(0)==' ')
                    operate=operate.substring(1);
                if(operate.charAt(operate.length()-1)!=';')
                    operate+=";";
                int tempIndex=-1;
                for(int j=0;j<operate.length();++j){
                    if(operate.charAt(j)!=';'){
                        if(tempIndex==-1)
                            tempIndex=j;
                    }
                    else{
                        String aimKey=operate.substring(tempIndex,j);
                        if(cache.get(aimKey)==null)
                            System.out.print("null;");
                        else{
                            System.out.print(cache.get(aimKey).getContent()+";");
                        }
                        tempIndex=-1;
                    }
                }
                System.out.println();
            }
        }
    }
}
class Element{
    private Element last=null;
    private Element next=null;
    private String content=null;
    private String key;
    public void setLast(Element last){this.last=last; }
    public void setNext(Element next){this.next=next;}
    public Element(String key,String con){
        content=con;
        this.key=key;
    }
    public String getKey(){return key;}
    public void setContent(String con){content=con;}
    public String getContent(){return content;}
    public Element getLast(){return last;}
    public Element getNext(){return next;}
}
class Cache{
    private Scanner in=new Scanner(System.in);
    private int size=0;
    private int maxSize=0;
    private Element head=null;
    private Element tail=null;

    private Map<String,Element> map= new HashMap<String,Element>();
    public Cache(int maxSize){
        this.maxSize=maxSize;
    }
    private Element remove(Element aim){
        if(aim.getLast()==null&&aim.getNext()==null){
            map.remove(aim.getKey());
        }
        else if(head.equals(aim)){
            aim.getNext().setLast(null);
            head=aim.getNext();
            aim.setNext(null);
            map.remove(aim.getKey());
        }
        else if(tail.equals(aim)){
            aim.getLast().setNext(null);
            tail=aim.getLast();
            aim.setLast(null);
            map.remove(aim.getKey());
        }
        else{
            aim.getLast().setNext(aim.getNext());
            aim.getNext().setLast(aim.getLast());
            aim.setLast(null);
            aim.setNext(null);
            map.remove(aim.getKey());
        }
        size--;
        return aim;
    }
    public void put(Element newElement){
        //cache为空
        if(size==0){
            head=newElement;
            tail=head;
        }
        //cache不为空
        else{
            //key已存在
            if(map.containsKey(newElement.getKey())){
                remove(map.get(newElement.getKey()));
            }
            //key不存在
            else if(size>=maxSize){
                remove(head);
            }
            tail.setNext(newElement);
            newElement.setLast(tail);
            tail=newElement;
        }
        size++;
        map.put(newElement.getKey(),newElement);
        //printLink();
    }
    public Element get(String key){
        if(!map.containsKey(key))
            return null;
        else if(tail!=map.get(key)){
            put(remove(map.get(key)));
        }
        //printLink();
        return map.get(key);
    }
}