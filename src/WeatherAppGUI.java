import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class WeatherAppGUI extends javax.swing.JFrame {
    private JSONObject weatherData;

    public WeatherAppGUI() {
        super("Weather App");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setSize(450, 650);
            setLocationRelativeTo(null);
            setLayout(null);
            setResizable(false);
            addGuiComponents();

    }


    private void addGuiComponents() {
        JLabel background = new JLabel(loadImage("src/img/bg.png"));
        background.setBounds(0, 0, 450, 650);
        add(background);

        JTextField searchTextField = new JTextField();

        //can change Font size, style
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        background.add(searchTextField);

        JLabel weatherConditionImage = new JLabel(loadImage("src/img/cloud.png"));
        weatherConditionImage.setBounds(0, 125, 450,217);
        background.add(weatherConditionImage);

        JLabel temperatureText = new JLabel("25 C°");
        temperatureText.setFont(new Font("Dialog", Font.PLAIN, 48));
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(temperatureText);

        JLabel conditionDescriptionText = new JLabel("Sunny");
        conditionDescriptionText.setFont(new Font("Dialog", Font.PLAIN, 32));
        conditionDescriptionText.setBounds(0, 405, 450, 36);
        conditionDescriptionText.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(conditionDescriptionText);


        JLabel humidityImage = new JLabel(loadImage("src/img/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        background.add(humidityImage);

        JLabel HumidityText = new JLabel("<html><b>Humidity</b> 100 %<html>");
        HumidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        HumidityText.setBounds(90, 500, 85, 80);
        background.add(HumidityText);

        JLabel windspeedImage = new JLabel(loadImage("src/img/windspeed.png"));
        windspeedImage.setBounds(220, 500, 74, 66);
        background.add(windspeedImage);

        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h<html>");
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        windspeedText.setBounds(310, 500, 95, 80);
        background.add(windspeedText);

        JButton searchButton = new JButton(loadImage("src/img/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTextField.getText();

                if(userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                weatherData = WeatherApp.getWeatherData(userInput);
                String weatherCondition = (String) weatherData.get("weather_condition");
                switch(weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/img/sunny.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/img/cloud.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/img/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/img/snow.png"));
                        break;
                }
//              data update
                double temperature = (Double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C°");

                conditionDescriptionText.setText(weatherCondition);

                long humidity = (Long) weatherData.get("humidity");
                HumidityText.setText("<html><b>Humidity</b> "+ humidity +" %</html>");

                double windspeed = (Long) weatherData.get("humidity");
                windspeedText.setText("<html><b>Windspeed</b> "+ windspeed +" km/h</html>");


            }
        });
        background.add(searchButton);

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
