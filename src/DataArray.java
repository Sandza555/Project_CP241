import javax.swing.*;

public class DataArray implements Interface{
    private int total;
    private Data list[];
    int current,size;

    public DataArray()
    {
        this.Array();
    }

    public void Array()
    {
        current= -1;
        total = 100;
        size=0;
        list = new Data[total];

    }

    public void insert(Data e) throws Exception
    {
        if (full())
        {
            JOptionPane.showMessageDialog(null,"List is Full");
            throw new Exception("List is full");
        }else
        {
                    size++;
                if (size == 1) {
                    current++;
                    list[current] = e;
                } else {
                    for (int i = size - 1; i > current + 1; i--)
                        list[i] = list[i - 1];
                    current++;
                    list[current] = e;
                }

        }
    }

    public Data retrieve() throws Exception
    {
        if (empty())
        {
            JOptionPane.showMessageDialog(null,"List is empty");
            throw new Exception("List is empty");
        }
        else
        {
                return list[current];

        }
    }

    public void delete() throws Exception
    {
        if (empty())
        {
            JOptionPane.showMessageDialog(null,"List is empty");
            throw new Exception("List is empty");
        }else
        {
            if (size == 1)
                current = -1;
            else if (current==size-1)
                current = 0;
            else
            {
                for(int i=current;i<size-1;i++)
                    list[i]=list[i+1];
                current = 0;
            }
            size--;
        }
    }

    public boolean empty()
    {
        if(size==0)
            return true;
        else
            return false;
    }

    public boolean full()
    {
        if (size == total)
            return true;
        else
            return false;
    }

    public void findNext() throws Exception
    {
        if (empty())
        {
            JOptionPane.showMessageDialog(null,"List is empty");
            throw new Exception("List is empty");
        }else
        {
            if (current != size-1)
                current = current+1;
            else {
                current = 0;
            }
        }
    }



    public void findPrev() throws Exception {
        if (empty())
        {
            JOptionPane.showMessageDialog(null,"List is empty");
            throw new Exception("List is empty");
        }else
        {
            if (current != 0)
                current = current-1;
            else {
                current = size-1;
            }
        }
    }


    public int getSize()
    {
        return size;
    }

    public boolean searchFor(int ch, String key,int check,Data e) throws Exception {
        boolean found = false;
        if (ch == 1){
                for(int i = 0; i <check;i++){
                    if (e.getName().equals(key)) {
                        found = true;
                        break;
                    }
                }}
        else if (ch == 2){
                for(int i = 0; i <check;i++){
                    if (e.getGender().equals(key)) {
                        found = true;
                        break;
                    }
                }}
        else if (ch == 3){
                for(int i = 0; i <check;i++){
                    if (e.getCareer().equals(key)) {
                        found = true;
                        break;
                    }
                }}
        else if (ch == 4){
                for(int i = 0; i <check;i++){
                    if (e.getAffiliation().equals(key)) {
                        found = true;
                        break;
                    }
                }}
        else if (ch == 5){
                for(int i = 0; i <check;i++){
                    if (e.getNationality().equals(key)) {
                        found = true;
                        break;
                    }
                }}
        return found;
        }

    }

