public interface Interface {
    public void Array();

    public void insert(Data e) throws Exception;
    public int getSize();

    public Data retrieve() throws Exception;

    public boolean empty();

    public void delete() throws Exception;

    public void findNext() throws Exception;

    public void findPrev() throws Exception;

    public boolean full();


    public boolean searchFor(int ch, String key,int size,Data e) throws Exception;
}
