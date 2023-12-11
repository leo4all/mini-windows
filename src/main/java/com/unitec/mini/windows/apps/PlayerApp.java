package com.unitec.mini.windows.apps;
import com.unitec.mini.windows.LoginForm;
import com.unitec.mini.windows.logic.User;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.*;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import jaco.mp3.player.MP3Player;
import java.awt.Image;
import java.io.FileInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.swing.filechooser.FileSystemView;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class PlayerApp extends JInternalFrame  implements AppInterface {
    boolean playing = false;
    String UserLoging=LoginForm.getUserLoging();
    String dataPath = "data.flw";
    String musicPath = "src/main/users/" + UserLoging + "/Music";
    long songSeek = 0;
    String currentSongName = "";
    MP3Player songPlayer;
    private FileSystemView fileSystemView;
    boolean finder;
    public PlayerApp(boolean finder,String Path,String namesong) {
        this.finder=finder;
            initComponents();
            if(finder){
            //musicPath=Path;
            //startMusic();
            playSong(namesong,false);
            songPlayer.play();
            songTitleLbl.setText(namesong);
            }else{
            startMusic();
            
        try {
            initializeDataFile();
            loadSongs();
            playLastSong();
        } catch (Exception e) {
            e.printStackTrace();
        }
            }
        Volumen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                songPlayer.stop();
            }
        });
        panelList.addListSelectionListener(new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()){
            JList source = (JList)event.getSource();
            String selected = source.getSelectedValue().toString();
            changeSong();
        }
    }
});
    }
    public void startMusic() {
    try {
        String musicFolderPath = "src/main/users/" + UserLoging + "/Music";
        File selectedFolder = new File(musicFolderPath);
        if (!selectedFolder.exists()) {
            return;
        }
        musicPath = selectedFolder.getCanonicalPath();
        songSeek = 0;
        currentSongName = "";
        updateData(musicPath, songSeek, currentSongName);
        loadSongs();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
     private long getTotalDuration(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Bitstream bitstream = new Bitstream(fileInputStream);
            int totalFrames = bitstream.readFrame().max_number_of_frames(totalFrames(fileInputStream));

            float frameRate = bitstream.readFrame().max_number_of_frames(totalFrames(fileInputStream)) / bitstream.readFrame().max_number_of_frames(totalFrames(fileInputStream));
            return (long) (totalFrames / frameRate);
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
            return 0;
        }
    }
    private int totalFrames(FileInputStream fileInputStream) throws IOException {
        Bitstream bitstream = new Bitstream(fileInputStream);
        int totalFrames = 0;
        while (true) {
            try {
                bitstream.readFrame();
                totalFrames++;
            } catch (JavaLayerException e) {
                break;
            }
        }
        return totalFrames;
    }
    private void initializeDataFile() throws Exception {
        File data = new File("data.flw");

        removeAllSongs();
        if (!data.exists()) {
            data.createNewFile();
            initializeDefaultData(data);
        } else {
            loadDataFromDisk(data);
        }
    }
    private void initializeDefaultData(File data) throws Exception {
        RandomAccessFile rfdata = new RandomAccessFile(data, "rw");
        rfdata.writeUTF("");
        rfdata.writeLong(0);
        rfdata.writeUTF("");
        rfdata.close();
        songTitleLbl.setText("NOT PLAYING");
        setAllBtns(false);
    }
    private void loadDataFromDisk(File data) throws Exception {
        RandomAccessFile rfdata = new RandomAccessFile(data, "rw");
        rfdata.seek(0);
        musicPath = rfdata.readUTF();
        songSeek = rfdata.readLong();
        currentSongName = rfdata.readUTF();
        loadSongs();
        playLastSong();
        rfdata.close();
    }
    private void updateData(String musicPath, long songSeek, String songName) {
        try {
            RandomAccessFile rfdata = new RandomAccessFile(dataPath, "rw");
            rfdata.writeUTF(musicPath);
            rfdata.writeLong(songSeek);
            rfdata.writeUTF(songName);
            rfdata.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ArrayList<String> getSongNamesFromPath(String musicPath) {
        if (musicPath.equals("")) {
            return new ArrayList<String>();
        }
        File folder = new File(musicPath);
        ArrayList<String> songs = new ArrayList<>();
        for (File f : folder.listFiles()) {
            if (f.getName().endsWith(".mp3")) {
                songs.add(f.getName().replace(".mp3", ""));
            }
        }
        return songs;
    }
    private void setAllBtns(boolean enabled) {
        BtnPlay.setEnabled(enabled);
        Btnback.setEnabled(enabled);
        forwardBtn.setEnabled(enabled);
    }
    private void playLastSong() {
        if (currentSongName.equals("")) {
            songTitleLbl.setText("NOT PLAYING");
            setAllBtns(false);
        } else {
            boolean found = false;
            for (int i = 0; i < panelList.getModel().getSize(); i++) {
                String n = panelList.getModel().getElementAt(i);
                System.out.println(n);
                if (n.equals(currentSongName)) {
                    String path = musicPath + File.separator + currentSongName + ".mp3";
                    songPlayer = new MP3Player(new File(path));
                    songTitleLbl.setText(currentSongName);
                    playing = false;
                    BtnPlay.setText("PLAY");
                    setAllBtns(true);
                    found = true;
                    continue;
                }
                if (found) {
                    String name = panelList.getModel().getElementAt(i);
                    String path = musicPath + File.separator + name + ".mp3";
                    songPlayer.addToPlayList(new File(path));
                }
            }
        }
    }
    private void loadSongs() {
        ArrayList<String> songs = getSongNamesFromPath(musicPath);
        panelList.removeAll();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String song : songs) {
            model.addElement(song);
        }
        panelList.setModel(model);
        setAllBtns(false);
        songTitleLbl.setText("NOT PLAYING");
    }
    private void removeAllSongs() {
        DefaultListModel<String> model = new DefaultListModel<>();
        panelList.setModel(model);
    }
    private void playSong(String songName,boolean estado) {
        String path;
        if(estado){
          path = musicPath + File.separator + songName + ".mp3";
        }else{
             path = musicPath + File.separator + songName;
        }
            
        if (songPlayer != null) {
            songPlayer.stop();
        }
        songPlayer = new MP3Player(new File(path));
        songPlayer.play();
        playing = true;
        BtnPlay.setText("PAUSE");
         songTitleLbl.setText(currentSongName);
    }
    public void updateList() {
    ArrayList<String> updateList = getSongNamesFromPath(musicPath);
    DefaultListModel<String> model = new DefaultListModel<>();
    for (int i = 0; i < updateList.size(); i++) {
        int j = i + 1; 
        model.add(i, j + " | " + updateList.get(i));
    }
    panelList.setModel(model);
    }
    private void volumeUp(Double valueToPlusMinus) {
    volumeControl(valueToPlusMinus);
}
private void volumeDown(Double valueToPlusMinus) {
    volumeControl(-valueToPlusMinus);
}
private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {
    int volumeValue = Volumen.getValue();
    float newVolume = volumeValue / 100.0f;
    adjustSystemVolume(newVolume);
}
private void adjustSystemVolume(float newVolume) {
        try {
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            for (Mixer.Info info : mixerInfo) {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if (port.isControlSupported(FloatControl.Type.VOLUME)) {
                        FloatControl volumeControl = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
                        volumeControl.setValue(newVolume);
                    }
                    port.close();
                }
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
private void volumeControl(Double valueToPlusMinus) {
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    for (Mixer.Info mixerInfo : mixers) {
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        Line.Info[] lineInfos = mixer.getTargetLineInfo();
        for (Line.Info lineInfo : lineInfos) {
            Line line = null;
            boolean opened = true;
            try {
                line = mixer.getLine(lineInfo);
                opened = line.isOpen() || line instanceof Clip;
                if (!opened) {
                    line.open();
                }
                FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                setVolume(volControl, valueToPlusMinus);
            } catch (LineUnavailableException lineException) {
            } catch (IllegalArgumentException illException) {
            } finally {
                if (line != null && !opened) {
                    line.close();
                }
            }
        }
    }
}
private void setVolume(FloatControl volControl, Double valueToPlusMinus) {
    float currentVolume = volControl.getValue();
    Double volumeToCut = valueToPlusMinus;
    float changedCalc = (float) (currentVolume + (double) volumeToCut);
    volControl.setValue(changedCalc);
}
    public ImageIcon setImageWithSize(ImageIcon originalIcon, int width, int height) {
    try {
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    public void ImageofMusic() {
    try {
            int selectedIndex = panelList.getSelectedIndex();
            String selectedSongName = panelList.getSelectedValue();
            File play1 = new File(musicPath + File.separator + selectedSongName+".mp3");
            fileSystemView = FileSystemView.getFileSystemView();
            ImageIcon fileIcon = (ImageIcon) fileSystemView.getSystemIcon(play1);
            Imagecancion.setIcon(setImageWithSize(fileIcon,54,54));
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Select a song from the playlist", null, JOptionPane.INFORMATION_MESSAGE);
        }
}
    private String getCurrentSongName() {
    for (int i = 0; i < panelList.getModel().getSize(); i++) {
        String n = panelList.getModel().getElementAt(i);
        if (n.equals(currentSongName)) {
            return currentSongName;
        }
    }
        System.out.println("nada");
    return "";
}
    public void changeSong(){
     if (songPlayer != null) {
            songPlayer.stop();
        }
        if (panelList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione primero una cancion para reproducir.");
            return;
        }
        currentSongName = panelList.getSelectedValue();
        updateData(musicPath, songSeek, currentSongName);
        songTitleLbl.setText(currentSongName);
        File songFile = new File(musicPath + File.separator + currentSongName + ".mp3");
        songPlayer = new MP3Player(songFile);
        songPlayer.play();
        playing = true;
        BtnPlay.setText("PAUSE");
        setAllBtns(true);
        updateData(musicPath, songSeek, currentSongName);
        ImageofMusic();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Top_Panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Btnback = new javax.swing.JButton();
        BtnPlay = new javax.swing.JButton();
        songTitleLbl = new javax.swing.JLabel();
        BtnStop = new javax.swing.JButton();
        forwardBtn = new javax.swing.JButton();
        Volumen = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Imagecancion = new javax.swing.JLabel();
        jPanel_Main_Panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelList = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Media Player");

        Btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImageAnterior.png"))); // NOI18N
        Btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnbackActionPerformed(evt);
            }
        });

        BtnPlay.setText("Play");
        BtnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPlayActionPerformed(evt);
            }
        });

        songTitleLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        songTitleLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songTitleLbl.setText("SONG TITLE");

        BtnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NormaStop.png"))); // NOI18N
        BtnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnStopActionPerformed(evt);
            }
        });

        forwardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImageSiguiente.png"))); // NOI18N
        forwardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("--");

        jLabel2.setText("++");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Imagecancion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(songTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(Btnback)
                        .addGap(18, 18, 18)
                        .addComponent(BtnPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(forwardBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(Volumen, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(songTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(Volumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(3, 3, 3))
                            .addComponent(forwardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btnback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Imagecancion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_Top_PanelLayout = new javax.swing.GroupLayout(jPanel_Top_Panel);
        jPanel_Top_Panel.setLayout(jPanel_Top_PanelLayout);
        jPanel_Top_PanelLayout.setHorizontalGroup(
            jPanel_Top_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Top_PanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        jPanel_Top_PanelLayout.setVerticalGroup(
            jPanel_Top_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelList.setForeground(new java.awt.Color(255, 255, 255));
        panelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                panelListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(panelList);

        javax.swing.GroupLayout jPanel_Main_PanelLayout = new javax.swing.GroupLayout(jPanel_Main_Panel);
        jPanel_Main_Panel.setLayout(jPanel_Main_PanelLayout);
        jPanel_Main_PanelLayout.setHorizontalGroup(
            jPanel_Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Main_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_Main_PanelLayout.setVerticalGroup(
            jPanel_Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Main_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Add");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save songs");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Open user folder");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel_Main_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_Top_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Top_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_Main_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showOpenDialog(this);
            File selectedFolder = chooser.getSelectedFile();
            if (selectedFolder == null) {
                return;
            }
            musicPath = selectedFolder.getCanonicalPath();
            songSeek = 0;
            currentSongName = "";
            updateData(musicPath, songSeek, currentSongName);
            loadSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void BtnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPlayActionPerformed
        if (songPlayer != null) {
            if (songPlayer.isStopped() || songPlayer.isPaused()) {
                playing = true;
                BtnPlay.setText("Play");
                if (!songPlayer.isStopped()) {
                    songPlayer.play();
                } else {
                    playSong(currentSongName,true);
                }
            } else {
                playing = false;
                BtnPlay.setText("Pause");
                songPlayer.pause();
            }
            if(finder){
            ImageofMusic();
            }
        }
    }//GEN-LAST:event_BtnPlayActionPerformed

    private void BtnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnStopActionPerformed
        if (songPlayer != null) {
            playing = false;
            BtnPlay.setText("PLAY");
            songPlayer.stop();
            ImageofMusic();
        }
    }//GEN-LAST:event_BtnStopActionPerformed

    private void forwardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardBtnActionPerformed
        if (songPlayer != null) {
            songPlayer.skipForward();
            for (int i = 0; i < panelList.getModel().getSize(); i++) {
                String n = panelList.getModel().getElementAt(i);
                if (n.equals(currentSongName)) {
                    if (i + 1 == panelList.getModel().getSize()) {
                        currentSongName = panelList.getModel().getElementAt(0);
                    } else {
                        currentSongName = panelList.getModel().getElementAt(i + 1);
                    }
                    songTitleLbl.setText(currentSongName);
                    updateData(musicPath, songSeek, currentSongName);
                    playSong(currentSongName,true);
                    break;
                }
            }
            ImageofMusic();
        }
    }//GEN-LAST:event_forwardBtnActionPerformed

    private void BtnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnbackActionPerformed
        if (songPlayer != null) {
            songPlayer.skipBackward();
            for (int i = 0; i < panelList.getModel().getSize(); i++) {
                String n = panelList.getModel().getElementAt(i);
                if (n.equals(currentSongName)) {
                    if (i - 1 == -1) {
                        currentSongName = panelList.getModel().getElementAt(0);
                    } else {
                        currentSongName = panelList.getModel().getElementAt(i - 1);
                    }
                    songTitleLbl.setText(currentSongName);
                    updateData(musicPath, songSeek, currentSongName);
                    playSong(currentSongName,true);
                    break;
                }
            }
            ImageofMusic();
        }
    }//GEN-LAST:event_BtnbackActionPerformed

    private void panelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_panelListValueChanged

    }//GEN-LAST:event_panelListValueChanged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (musicPath == null || musicPath.isEmpty()) {
        return;
    }
        if (songPlayer != null) {
            if(!songPlayer.isStopped() || !songPlayer.isPaused()){
        songPlayer.stop();
            }
    }
    String destinationFolderPath = "src/main/users/" + UserLoging + "/Music";
    try {
        File sourceFolder = new File(musicPath);
        File destinationFolder = new File(destinationFolderPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                Path sourcePath = file.toPath();
                Path destinationPath = new File(destinationFolder, file.getName()).toPath();
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        JOptionPane.showInternalInputDialog(null,"Se ha modificado./n Cambie de al user folder en file.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         startMusic();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    static boolean guardar=true;
    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPlay;
    private javax.swing.JButton BtnStop;
    private javax.swing.JButton Btnback;
    private javax.swing.JLabel Imagecancion;
    private javax.swing.JSlider Volumen;
    private javax.swing.JButton forwardBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_Main_Panel;
    private javax.swing.JPanel jPanel_Top_Panel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> panelList;
    private javax.swing.JLabel songTitleLbl;
    // End of variables declaration//GEN-END:variables
}
