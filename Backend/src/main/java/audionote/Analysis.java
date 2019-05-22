package audionote;
import java.util.*;
import java.io.*;

public class Analysis{

    private ArrayList<Word> input = new ArrayList<Word>();
    private ArrayList<KeyWord> order = new ArrayList<KeyWord>();
    private ArrayList<String> common = new ArrayList<String>();

    public Analysis(ArrayList<Word> w){
        input = w;
        loadCommon(5000);
        for(int i = 0; i < w.size(); i++){
            String lower = input.get(i).getWord().toLowerCase();
            String normed = lower.replaceAll("\\p{Punct}", "");
            if(!common.contains(normed)) {
                addWord(input.get(i));
            }
        }
        sort();
    }

    public void loadCommon(int n) {
        File file = new File(getClass().getClassLoader().getResource("commonReference.txt").getFile());
        try {
            Scanner input = new Scanner(file);
            int i = 0;
            while (input.hasNextLine()) {
                if (i >= n) {
                    break;
                }
                common.add(input.nextLine());
                i++;
            }
        }
        catch(IOException e) {
            System.err.println("error");
        }
    }

    public ArrayList<KeyWord> getList(){
        return order;
    }

    private void addWord(Word w){

        for(int i = 0; i < order.size(); i++){
            if(w.getWord().equals(order.get(i).getWord())){
                order.get(i).addOccurence(w);
                return;
            }
        }
        order.add(new KeyWord(w));
        /*
        Collections.sort(order, new Comparator<KeyWord>(){

            @Override
            public int compare(KeyWord k1, KeyWord k2){
                int c;
                c = k1.getFrequency() - k2.getFrequency();
                if(c == 0){
                    c = k1.getWord().compareTo(k2.getWord());
                }
                return c;
            }
        });
         */

    }
    public void sort(){
        Collections.sort(order, new Comparator<KeyWord>(){

            @Override
            public int compare(KeyWord k1, KeyWord k2){
                int c;
                c = k2.getFrequency() - k1.getFrequency();
                if(c == 0){
                    c = k1.getWord().compareTo(k2.getWord());
                }
                return c;
            }
        });
    }

}

