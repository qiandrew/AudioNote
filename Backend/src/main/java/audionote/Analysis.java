package audionote;
import java.util.*;

public class Analysis{
    private ArrayList<Word> input = new ArrayList<Word>();
    private ArrayList<KeyWord> order = new ArrayList<KeyWord>();

    public Analysis(ArrayList<Word> w){
        input = w;
        for(int i = 0; i < w.size(); i++){
            addWord(input.get(i));
        }
    }

    public ArrayList<KeyWord> getList(){
        return order;
    }

    private void addWord(Word w){
        for(int i = 0; i < order.size(); i++){
            if(w.getWord() == order.get(i).getWord()){
                order.get(i).addOccurence(w);
                break;
            }
        }
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

    }

}

