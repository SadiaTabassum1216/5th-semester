import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Set<String>> transactionDatabase = readFile();
        Set<Set<String>> uniqueItemset = new HashSet<Set<String>>();
        HashMap<Set<String>, Integer> result=generateUniqueItems(transactionDatabase,uniqueItemset);
        HashMap<Set<String>, Integer> recommendationList=generateUniqueItems(transactionDatabase,uniqueItemset);
        List<Map.Entry<Set<String>, Integer>> list = new ArrayList<>(result.entrySet());

        ArrayList<Set<String>> result1 = new ArrayList<Set<String>>();
        for (Map.Entry<Set<String>, Integer> entry : list) {
            result1.add(entry.getKey());
        }

        ArrayList<String> listOfItems = new ArrayList<String>();
        for (Set<String> set : result1) {
            for (String str : set) {
                listOfItems.add(str);
            }
        }
        System.out.println(listOfItems);
        generateCandidateKeys(transactionDatabase,result);
        ArrayList<String> cart= new ArrayList<String>();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("Enter item: ");
            String item=sc.next();
            cart.add(item);
            Set<String> recommendations=generateRecommendations(cart,result,listOfItems);
            System.out.println(recommendations);
            System.out.println("Y/N:");
            String choice = sc.next();
            if(choice.equals("n")){break;}
        }

    }

    public static void Apriori(){}
    public static ArrayList<Set<String>> readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Book1.csv"));
        ArrayList <Set<String>> transactionDatabase = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            String[] items = line.split(",");
            Set<String> transaction = new HashSet<>();
            for (String item : items) {
                transaction.add(item);
            }

            transactionDatabase.add(transaction);
        }
        reader.close();
        return transactionDatabase;
    }

    public static HashMap<Set<String>, Integer> generateUniqueItems(ArrayList <Set<String>> transactionDatabase,Set<Set<String>> uniqueItemset){
        HashMap<Set<String>, Integer> result=new HashMap<Set<String>, Integer>();
        Iterator<Set<String>> itr = transactionDatabase.iterator();
        while(itr.hasNext()){
            Iterator<String> itr2=itr.next().iterator();
            while(itr2.hasNext()){
                Set<String> item=new HashSet<String>();
                item.add(itr2.next());
                if(!result.containsKey(item)) result.put(item,1);
                else{
                    result.put( item,(result.get(item))+1);
                }
            }}
        uniqueItemset=result.keySet();

        return result;
    }

    public static HashSet<String> generateRecommendations(ArrayList<String> cart,HashMap<Set<String>, Integer> result,ArrayList<String> listOfItems){
        Set<String> premises=new HashSet<String>();
        Set<String> combined=new HashSet<String>();
        HashSet<String> recommendations=new HashSet<String>();
        for(String item:cart) {
            premises.add(item);
            combined.add(item);
        }
        System.out.println(premises);
        System.out.println(combined);

        for (String item: listOfItems){
            combined.add(item);
            double confidence=0,premiseSupport=0,combinedSupport=0;
            Iterator itr=result.entrySet().iterator();
            while(itr.hasNext()) {
                Map.Entry mapElement=(Map.Entry)itr.next();
                if(mapElement.getKey().equals(combined)) {combinedSupport=(int)mapElement.getValue();}
                if(mapElement.getKey().equals(premises)) {premiseSupport=(int)mapElement.getValue();}
                if(premiseSupport!=0){confidence=combinedSupport/premiseSupport;}
                if(confidence>0){recommendations.add(item);}
            }
            combined.remove(item);
        }
        Iterator<String> iterator = recommendations.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if(cart.contains(element)){
                iterator.remove();
            }
        }
        return recommendations;
    }

    public static void generateCandidateKeys(ArrayList <Set<String>> transactionDatabase, HashMap<Set<String>, Integer> result){
        int k=1;

        while(true){HashMap<Set<String>, Integer> temp=new HashMap<>();
            temp=result;
            HashMap<Set<String>, Integer> temp2=new HashMap<>();
            Iterator itr4=temp.entrySet().iterator();
            while(itr4.hasNext()){
                Map.Entry mapElement=(Map.Entry)itr4.next();
                Set<String> X= (Set<String>) mapElement.getKey();

                Iterator itr5=temp.entrySet().iterator();
                while(itr5.hasNext()){
                    Map.Entry mapElement1=(Map.Entry)itr5.next();
                    Set<String> Y= (Set<String>) mapElement1.getKey();
                    Set<String> item=new HashSet<>();
                    item.addAll(X);
                    item.addAll(Y);
                    if(item.size()>k) {
                        temp2.put(item,0);}
                }
            }


            HashMap<Set<String>, Integer> temp3=new HashMap<>();
            temp3=temp2;


            Iterator<Set<String>> itr = transactionDatabase.iterator();

            while(itr.hasNext()){
                Set<String> item=itr.next();
                Iterator itr2=temp2.entrySet().iterator();

                while(itr2.hasNext()){
                    Map.Entry mapElement=(Map.Entry)itr2.next();
                    Set<String> item2= (Set<String>) mapElement.getKey();
                    if(item.containsAll(item2)) {
                        temp3.put(item2,temp3.get(item2)+1);}
                }}

            Iterator itr3=temp3.entrySet().iterator();
            while(itr3.hasNext()){
                Map.Entry mapElement=(Map.Entry)itr3.next();
                if((int)mapElement.getValue()<1){
                    itr3.remove();}
            }
            System.out.println(k);
            if(temp3.size()==0) {break;}
            result.putAll(temp3);
            k++;}

        Iterator itr3=result.entrySet().iterator();
        while(itr3.hasNext()){
            Map.Entry mapElement=(Map.Entry)itr3.next();
            System.out.println(mapElement.getKey()+"  "+mapElement.getValue());
        }
    }

}