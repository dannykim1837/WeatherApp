import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class WeatherAppGUI extends javax.swing.JFrame {
    public WeatherAppGUI() {
        super("Weather App");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setSize(450, 600);
            setLocationRelativeTo(null);
            setLayout(null);
            setResizable(false);
            addGuiComponents();

    }


    private void addGuiComponents() {
        JTextField searchTextField = new JTextField();

        //can change Font size, style
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchTextField);

        JButton searchButton = new JButton(loadImage("src/img/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        add(searchButton);

        JLabel weatherConditionImage = new JLabel(loadImage("src/img/cloud.png"));
        weatherConditionImage.setBounds(0, 20, 450,300);
        add(weatherConditionImage);

        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setFont(new Font("Dialog", Font.PLAIN, 35));
        temperatureText.setBounds(0, 230, 450, 45);
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        JLabel conditionDescriptionText = new JLabel("Cloudy");
        conditionDescriptionText.setFont(new Font("Dialog", Font.PLAIN, 35));
        conditionDescriptionText.setBounds(0, 300, 450, 45);
        conditionDescriptionText.setHorizontalAlignment(SwingConstants.CENTER);
        add(conditionDescriptionText);

        JLabel TodayText = new JLabel("Today");
        TodayText.setFont(new Font("Dialog", Font.PLAIN, 20));
        TodayText.setBounds(10, 400, 450, 45);
        TodayText.setHorizontalAlignment(SwingConstants.LEFT);
        add(TodayText);


        JLabel humidityImage = new JLabel(loadImage("src/img/humidity.png"));

    }

    private ImageIcon loadImage(String resourcePath) {
        try{
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);

        }catch(IOException e){
            e.printStackTrace();
        }



        System.out.println("Could not found resource: ");
        return null;
    }
}
