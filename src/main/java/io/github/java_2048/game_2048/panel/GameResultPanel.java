package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.App;
import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.game.GameResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameResultPanel extends AppPanel {
    public GameResultPanel(GameResult result, int difficult) {
        // panel 기본 세팅
        setBackground();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 중앙에 두기 위함
        add(Box.createGlue());

        // 해당 패널에서 사용될 버튼 기본 값
        Dimension btnSize = new Dimension(150, 50);
        Font btnFont = MainFont.get().deriveFont(Font.BOLD, 30);

        int score = save_load(result.score(),difficult);

        if (result.isWin()) {
            // 이겼을 때
            JLabel title = new JLabel(" WIN");
            title.setFont(MainFont.get().deriveFont(Font.BOLD, 100));
            title.setMaximumSize(new Dimension(253, 0));
            title.setForeground(new Color(118, 111, 101));
            title.setAlignmentX(CENTER_ALIGNMENT);
            add(title);

            add(Box.createVerticalStrut(70));
        }
        else {
            // 졌을 때
            JLabel title = new JLabel(" GAME OVER");
            //크기가 너무 크면 이상함
            title.setFont(MainFont.get().deriveFont(Font.BOLD, 80));
            //옆으로 크기를 약간 더 늘림
            title.setMaximumSize(new Dimension(500, 0));
            title.setForeground(new Color(118, 111, 101));
            title.setAlignmentX(CENTER_ALIGNMENT);
            add(title);

            add(Box.createVerticalStrut(50));
        }


        //게임 결과 창
        JLabel resultlabel = new JLabel();
        //고정폭 글꼴로 바꿔주기 위함. 결과창을 띄울 때 가변폭 글꼴이면 보기 안좋음.
        Font fixedWidthFont = new Font("Monospaced", Font.BOLD, 30);

        resultlabel.setText("<html>" +
                "DIFFICULT&nbsp;&nbsp;" + difficult + "<br>" +
                "SCORE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + result.score() + "<br>" +
                "HIGH SCORE&nbsp;&nbsp;" + score + "<br>" +
                "MOVE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + result.move() + "<br>" +
                "</html>");


        resultlabel.setFont(fixedWidthFont);
        resultlabel.setMaximumSize(new Dimension(500, 0));
        resultlabel.setForeground(new Color(118, 111, 101));
        resultlabel.setAlignmentX(CENTER_ALIGNMENT);
        resultlabel.setAlignmentY(CENTER_ALIGNMENT);
        resultlabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultlabel);

        add(Box.createVerticalStrut(150));


        // 메인 버튼 생성
        JButton startBtn = new JButton("MAIN");
        startBtn.setMaximumSize(btnSize);
        startBtn.setFont(btnFont);
        startBtn.setForeground(Color.white);
        startBtn.setBackground(getBtnColor());
        startBtn.addActionListener(this::clickMainButton);
        startBtn.setAlignmentX(CENTER_ALIGNMENT);
        add(startBtn);

        // 높이 10 간격 추가
        add(Box.createVerticalStrut(50));

    }

    private void clickMainButton(ActionEvent event) {
        App.getInstance().changePanel(new MainPanel());
    }




    private int save_load(int score, int difficult) {
        int highscore=0;
        String str="difficult " + difficult;
        try {
            // 임시 파일을 생성
            File tempFile = new File("temp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            File file = new File("score.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));




            // 파일을 한 줄씩 읽어가면서 difficult 부분이 일치하면 업데이트
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith(str)) {
                    String[] parts = line.split(", ");
                    String highscoreStr = parts[1].substring(parts[1].lastIndexOf(" ") + 1);
                    highscore = Integer.parseInt(highscoreStr);

                    if (score > highscore)
                    {
                        highscore=score;
                        // 업데이트할 내용
                        String updateLine = "difficult " + difficult + ", highscore " + score;
                        line = updateLine;
                    }
                }
                //실제 파일에 쓰는 구문 임시파일을 이름을 바꿔 실제 파일로 대체 하기 때문에 끝까지 출력되어야 함
                writer.write(line + System.lineSeparator());
            }

            // 스트림 닫기
            reader.close();
            writer.close();


            // 기존 파일을 삭제하고 임시 파일을 기존 파일명으로 변경
            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    System.out.println("To change the file name is failed");
                }
            } else {
                System.out.println("To delete the file is failed");
            }

            System.out.println("File is updated");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return highscore;
    }
}