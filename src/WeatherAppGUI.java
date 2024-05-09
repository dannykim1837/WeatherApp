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
        JLabel background = new JLabel(loadImage("src/img/bg.png"));
        background.setBounds(0, 0, 450, 600);
        add(background);

        JTextField searchTextField = new JTextField();

        //can change Font size, style
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        background.add(searchTextField);

        JButton searchButton = new JButton(loadImage("src/img/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        background.add(searchButton);

        JLabel weatherConditionImage = new JLabel(loadImage("src/img/cloud.png"));
        weatherConditionImage.setBounds(0, 20, 450,300);
        background.add(weatherConditionImage);

        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setFont(new Font("Dialog", Font.PLAIN, 35));
        temperatureText.setBounds(0, 230, 450, 45);
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(temperatureText);

        JLabel conditionDescriptionText = new JLabel("Cloudy");
        conditionDescriptionText.setFont(new Font("Dialog", Font.PLAIN, 20));
        conditionDescriptionText.setBounds(0, 280, 450, 45);
        conditionDescriptionText.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(conditionDescriptionText);

        JLabel TodayText = new JLabel("Today");
        TodayText.setFont(new Font("Dialog", Font.PLAIN, 16));
        TodayText.setBounds(15, 400, 450, 45);
        TodayText.setHorizontalAlignment(SwingConstants.LEFT);
        background.add(TodayText);


        JLabel humidityImage = new JLabel(loadImage("src/img/humidity.png"));
        humidityImage.setBounds(15, 480, 74, 66);
        background.add(humidityImage);

        JLabel HumidityText = new JLabel("<html><b>Humidity</b> 100%<html>");
        HumidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        HumidityText.setBounds(105, 480, 88, 55);
        background.add(HumidityText);

        JLabel windspeedImage = new JLabel(loadImage("src/img/windspeed.png"));
        windspeedImage.setBounds(220, 480, 74, 66);
        background.add(windspeedImage);

        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h<html>");
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        windspeedText.setBounds(310, 480, 88, 55);
        background.add(windspeedText);


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
