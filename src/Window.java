import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Window {
    private JFrame window;
    private JPanel homePanel, insertPanel, resultPanel,backGround,searchPanel,seachResultPanel;
    private JLabel inNameL,inAffL,inNationL,inURLL,inInfoL,inGenL,inJobL,reName,reAff,reNation,reURL,reInfo,reGen,reJob,searchlabel,seLa,reSeName,reSeAff,reSeNation,reSeUrl,reSeInfo,reSeGen,reSeJob,inNick,reNick,reSeNick;
    private TextField inName,inAff,inNation,inURL,inInfo,searchField,textNick;
    private Button homeInsert, homeDelete, homeView, homeSearch, homeExit, resultHome, resultNext, resultPrev, insertHome, insertSubmit,URL,searchSubmit,searchHome,searchNext,searchPrev,seaHome,searchURL;
    private JComboBox career,gender,search;
    private String name,affiliation,nationality,url,info,gen,job,key,nick;
    String[] careerBox ={"-","Youtuber","KPop","JPop","Singer","Actor","VTuber","Model"};
    String[] genderBox ={"-","Male","Female","Other"};
    String[] searchBox = {"-","Name","Gender","Career","Affiliation","Nationality"};
    public static Interface datainfo,searchInfo;
    private int ch;




    public Window() {
        initialize();
    }

    public void initialize() {

        searchInfo = new DataArray();
        datainfo = new DataArray();
        window = new JFrame();
        window.setTitle("Idol Information Related");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(900, 600);
        window.setResizable(false);
        window.setVisible(true);
        window.setLayout(null);
        window.setLocation(300,100);

        backGround = new JPanel(new CardLayout());
        window.add(backGround);
        backGround.setBounds(0,0,900,600);

///////////////////////////////////// Home page ///////////////////////////////////////////////////
        homePanel = new JPanel();
        backGround.add(homePanel);
        homePanel.setLayout(null);
        homePanel.setBackground(Color.GRAY);

        homeInsert = new Button("Insert");
        homeInsert.setBounds(400,100,100,50);
        homePanel.add(homeInsert);
        homeInsert.setFocusable(false);
        homeInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(false);
                insertPanel.setVisible(true);
            }
        });

        homeDelete = new Button("Delete");
        homeDelete.setBounds(400,170,100,50);
        homePanel.add(homeDelete);
        homeDelete.setFocusable(false);
        homeDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    datainfo.delete();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null,"Deleted.");
            }
        });

        homeView = new Button("View");
        homeView.setBounds(400,240,100,50);
        homePanel.add(homeView);
        homeView.setFocusable(false);
        homeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(false);
                resultPanel.setVisible(true);
                try {
                    Data result = (Data) datainfo.retrieve();
                    updateResult(result);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        homeSearch = new Button("Search");
        homeSearch.setBounds(400,310,100,50);
        homePanel.add(homeSearch);
        homeSearch.setFocusable(false);
        homeSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(false);
                searchPanel.setVisible(true);

            }
        });

        homeExit = new Button("Exit");
        homeExit.setBounds(400,380,100,50);
        homePanel.add(homeExit);
        homeExit.setFocusable(false);
        homeExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });

////////////////////////////////////////  Insert Page ///////////////////////////////////////////////////////////
        insertPanel = new JPanel();
        backGround.add(insertPanel);
        insertPanel.setVisible(false);
        insertPanel.setLayout(null);
        insertPanel.setBackground(Color.gray);

        insertSubmit = new Button("Submit");
        insertSubmit.setBounds(700,500,100,50);
        insertPanel.add(insertSubmit);
        insertSubmit.setFocusable(false);
        insertSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                name = inName.getText();
                affiliation = inAff.getText();
                nationality = inNation.getText();
                url = inURL.getText();
                info = inInfo.getText();
                gen = gender.getSelectedItem().toString();
                job = career.getSelectedItem().toString();
                nick = textNick.getText();

if(!name.isEmpty() && !affiliation.isEmpty() && !nationality.isEmpty() && !url.isEmpty() && !info.isEmpty() && !gen.isEmpty() && !job.isEmpty()&& !nick.isEmpty()){
                inAff.setText("");
                inName.setText("");
                inNation.setText("");
                inURL.setText("");
                inInfo.setText("");
                textNick.setText("");

                Data data = new Data(name,affiliation,nationality,info,url ,gen, job,nick);
                try {
                    datainfo.insert(data);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                }}
else {
    JOptionPane.showMessageDialog(null,"Please Enter all.");
}
            }
        });

        insertHome = new Button("home");
        insertHome.setBounds(100,500,100,50);
        insertPanel.add(insertHome);
        insertHome.setFocusable(false);
        insertHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPanel.setVisible(false);
                homePanel.setVisible(true);
            }
        });

        career = new JComboBox<>(careerBox);
        career.setBounds(250,200,200,40);
        insertPanel.add(career);
        career.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                job = Objects.requireNonNull(career.getSelectedItem()).toString();
            }
        });
        inJobL = new JLabel("Career : ");
        inJobL.setBounds(100,200,200,40);
        inJobL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inJobL);

        gender = new JComboBox<>(genderBox);
        gender.setBounds(250,150,200,40);
        insertPanel.add(gender);
        gender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gen = Objects.requireNonNull(gender.getSelectedItem()).toString();
            }
        });
        inGenL = new JLabel("Gender : ");
        inGenL.setBounds(100,150,200,40);
        inGenL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inGenL);

        textNick = new TextField();
        insertPanel.add(textNick);
        textNick.setBounds(250,100,600,40);
        textNick.setFont(new Font("",Font.PLAIN,24));
        inNick = new JLabel("Nick name");
        inNick.setBounds(100,100,600,40);
        insertPanel.add(inNick);
        inNick.setFont(new Font("",Font.PLAIN,24));

        inName = new TextField();
        insertPanel.add(inName);
        inName.setBounds(250,50,600,40);
        inName.setFont(new Font("",Font.PLAIN,24));
        inNameL = new JLabel("Name : ");
        inNameL.setBounds(100,50,600,40);
        inNameL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inNameL);

        inAff = new TextField();
        insertPanel.add(inAff);
        inAff.setBounds(250,250,600,40);
        inAff.setFont(new Font("",Font.PLAIN,24));
        inAffL = new JLabel("Affiliation : ");
        inAffL.setBounds(100,250,200,40);
        inAffL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inAffL);

        inNation = new TextField();
        insertPanel.add(inNation);
        inNation.setBounds(250,300,600,40);
        inNation.setFont(new Font("",Font.PLAIN,24));
        inNationL = new JLabel("Nationality : ");
        inNationL.setBounds(100,300,200,40);
        inNationL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inNationL);

        inInfo = new TextField();
        insertPanel.add(inInfo);
        inInfo.setBounds(250,350,600,40);
        inInfo.setFont(new Font("",Font.PLAIN,24));
        inInfoL = new JLabel("Info : ");
        inInfoL.setBounds(100,350,200,40);
        inInfoL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inInfoL);

        inURL = new TextField();
        insertPanel.add(inURL);
        inURL.setBounds(250,400,600,40);
        inURL.setFont(new Font("",Font.PLAIN,24));
        inURLL = new JLabel("URL(to wiki) : ");
        inURLL.setBounds(100,400,200,40);
        inURLL.setFont(new Font("",Font.PLAIN,24));
        insertPanel.add(inURLL);




///////////////////////////////////////// Result Page  ///////////////////////////////////////////////////////
        resultPanel = new JPanel();
        backGround.add(resultPanel);
        resultPanel.setLayout(null);
        resultPanel.setBackground(Color.GRAY);
        resultPanel.setVisible(false);
        resultNext = new Button("Next");
        resultNext.setBounds(550,500,100,50);
        resultPanel.add(resultNext);
        resultNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    datainfo.findNext();
                    Data result = (Data) datainfo.retrieve();
                    updateResult(result);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        resultPrev = new Button("Previous");
        resultPrev.setBounds(250,500,100,50);
        resultPanel.add(resultPrev);
        resultPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    datainfo.findPrev();
                    Data result = (Data) datainfo.retrieve();
                    updateResult(result);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }


        });

        resultHome = new Button("Home");
        resultHome.setBounds(400,500,100,50);
        resultPanel.add(resultHome);
        resultHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                homePanel.setVisible(true);
            }
        });

        reName = new JLabel("Name : ");
        resultPanel.add(reName);
        reName.setBounds(100,50,400,40);
        reName.setFont(new Font("",Font.PLAIN,24));

        reNick = new JLabel("Nick name :");
        resultPanel.add(reNick);
        reNick.setBounds(100,100,400,40);
        reNick.setFont(new Font("",Font.PLAIN,24));

        reGen = new JLabel("Gender : ");
        resultPanel.add(reGen);
        reGen.setBounds(100,150,400,40);
        reGen.setFont(new Font("",Font.PLAIN,24));

        reJob = new JLabel("Career : ");
        resultPanel.add(reJob);
        reJob.setBounds(100,200,400,40);
        reJob.setFont(new Font("",Font.PLAIN,24));

        reAff = new JLabel("Affiliation : ");
        resultPanel.add(reAff);
        reAff.setBounds(100,250,400,40);
        reAff.setFont(new Font("",Font.PLAIN,24));

        reNation = new JLabel("Nationality : ");
        resultPanel.add(reNation);
        reNation.setBounds(100,300,350,40);
        reNation.setFont(new Font("",Font.PLAIN,24));

        reInfo = new JLabel("Info : ");
        resultPanel.add(reInfo);
        reInfo.setBounds(100,350,400,40);
        reInfo.setFont(new Font("",Font.PLAIN,24));

        reURL = new JLabel("URL(to wiki) : ");
        resultPanel.add(reURL);
        reURL.setBounds(100,400,200,40);
        reURL.setFont(new Font("",Font.PLAIN,24));

        URL = new Button("Click here");
        resultPanel.add(URL);
        URL.setBounds(250,400,400,40);
        URL.setFont(new Font("",Font.PLAIN,16));
        URL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Desktop.getDesktop().browse(new URL(url).toURI());

                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ///////////////////////////////////// Search Panel ///////////////////////////////////

        searchPanel = new JPanel();
        backGround.add(searchPanel);
        searchPanel.setLayout(null);
        searchPanel.setBackground(Color.GRAY);
        searchPanel.setVisible(false);

        search = new JComboBox<>(searchBox);
        searchPanel.add(search);
        search.setBounds(250,250,100,50);


        seLa = new JLabel();
        searchField = new TextField();
        searchSubmit = new Button("Submit");
        searchSubmit.setBounds(400,500,100,50);
        searchPanel.add(searchSubmit);
        searchField.setBounds(200,350,600,40);
        searchField.setFont(new Font("",Font.PLAIN,24));
        searchPanel.add(searchField);
        seLa.setText("");
        seLa.setText("Enter "+searchBox[ch]+" : ");
        searchPanel.add(seLa);
        seLa.setBounds(100,350,120,40);

        searchSubmit.setVisible(true);
        searchHome = new Button("Home");
        searchPanel.add(searchHome);
        searchHome.setBounds(100,500,100,50);
        searchHome.setFocusable(false);



        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ch = search.getSelectedIndex();
            }
        });
        searchHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.setVisible(false);
                homePanel.setVisible(true);

            }
        });
        searchSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ch == 0){
                    JOptionPane.showMessageDialog(null,"Please Select something.");
                }else{
                try {
                    key = searchField.getText();
                    for(int i = 0;i<datainfo.getSize();i++){
                        Data data = datainfo.retrieve();
                        int size = datainfo.getSize();
                        if (data.searchFor(ch, key,size,data)){
                            searchInfo.insert(data);}
                        datainfo.findNext();
                    }
                    searchPanel.setVisible(false);
                    seachResultPanel.setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }}});

        searchlabel = new JLabel("Select what you want to search.");
        searchPanel.add(searchlabel);
        searchlabel.setBounds(180,120,700,50);
        searchlabel.setFont(new Font("",Font.BOLD,36));

/////////////////////////////////////////////////// Search Result Panel /////////////////////////////////////////////////

        seachResultPanel = new JPanel();
        backGround.add(seachResultPanel);
        seachResultPanel.setLayout(null);
        seachResultPanel.setBackground(Color.GRAY);
        seachResultPanel.setVisible(false);

        searchNext = new Button("Next");
        searchNext.setBounds(550,500,100,50);
        seachResultPanel.add(searchNext);
        searchNext.setFocusable(false);
        searchNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    searchInfo.findNext();
                    Data result = (Data) searchInfo.retrieve();
                    updateSearchResult(result);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        searchPrev = new Button("Previous");
        searchPrev.setBounds(250,500,100,50);
        seachResultPanel.add(searchPrev);
        searchPrev.setFocusable(false);
        searchPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    searchInfo.findPrev();
                    Data result = (Data) searchInfo.retrieve();
                    updateSearchResult(result);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        seaHome = new Button("Home");
        seaHome.setBounds(400,500,100,50);
        seachResultPanel.add(seaHome);
        seaHome.setFocusable(false);
        seaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seachResultPanel.setVisible(false);
                homePanel.setVisible(true);
                try {
                    while(searchInfo.getSize() != 0){
                        searchInfo.delete();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        reSeName = new JLabel("Name : ");
        seachResultPanel.add(reSeName);
        reSeName.setBounds(100,50,400,40);
        reSeName.setFont(new Font("",Font.PLAIN,24));

        reSeNick = new JLabel("Nick name : ");
        seachResultPanel.add(reSeNick);
        reSeNick.setBounds(100,100,400,40);
        reSeNick.setFont(new Font("",Font.PLAIN,24));

        reSeGen = new JLabel("Gender : ");
        seachResultPanel.add(reSeGen);
        reSeGen.setBounds(100,150,400,40);
        reSeGen.setFont(new Font("",Font.PLAIN,24));

        reSeJob = new JLabel("Career : ");
        seachResultPanel.add(reSeJob);
        reSeJob.setBounds(100,200,400,40);
        reSeJob.setFont(new Font("",Font.PLAIN,24));

        reSeAff = new JLabel("Affiliation : ");
        seachResultPanel.add(reSeAff);
        reSeAff.setBounds(100,250,400,40);
        reSeAff.setFont(new Font("",Font.PLAIN,24));

        reSeNation = new JLabel("Nationality : ");
        seachResultPanel.add(reSeNation);
        reSeNation.setBounds(100,300,350,40);
        reSeNation.setFont(new Font("",Font.PLAIN,24));

        reSeInfo = new JLabel("Info : ");
        seachResultPanel.add(reSeInfo);
        reSeInfo.setBounds(100,350,400,40);
        reSeInfo.setFont(new Font("",Font.PLAIN,24));

        reSeUrl = new JLabel("URL(to wiki) : ");
        seachResultPanel.add(reSeUrl);
        reSeUrl.setBounds(100,400,200,40);
        reSeUrl.setFont(new Font("",Font.PLAIN,24));

        searchURL = new Button("Click here");
        seachResultPanel.add(searchURL);
        searchURL.setBounds(350,400,400,40);
        searchURL.setFont(new Font("",Font.PLAIN,16));
        searchURL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Desktop.getDesktop().browse(new URL(url).toURI());

                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
    private void updateResult(Data result) {
        reName.setText("Name : "+result.getName());
        reGen.setText("Gender : "+result.getGender());
        reJob.setText("Career : "+result.getCareer());
        reAff.setText("Affiliation : "+result.getAffiliation());
        reInfo.setText("Info : "+result.getGenKnow());
        reNation.setText("Nationality : "+result.getNationality());
        url = result.getUrl();
        reNick.setText("Nick name : "+result.getNickName());
    }

    private void updateSearchResult(Data result) {
        reSeName.setText("Name : "+result.getName());
        reSeGen.setText("Gender : "+result.getGender());
        reSeJob.setText("Career : "+result.getCareer());
        reSeAff.setText("Affiliation : "+result.getAffiliation());
        reSeInfo.setText("Info : "+result.getGenKnow());
        reSeNation.setText("Nationality : "+result.getNationality());
        url = result.getUrl();
        reNick.setText("Nick name : "+result.getNickName());
    }

}
