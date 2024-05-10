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
    private JScrollPane scrollPane;

    public WeatherAppGUI() {
            super("Weather App");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setSize(450, 700);
            setLocationRelativeTo(null);
            setResizable(false);
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null);
            mainPanel.setPreferredSize(new Dimension(450, 700));
            addGuiComponents(mainPanel);
            scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            setContentPane(scrollPane);


            ImageIcon image = new ImageIcon("src/img/logo.png");
            super.setIconImage(image.getImage());
    }


    private void addGuiComponents(JPanel panel) {
        JLabel background = new JLabel(loadImage("src/img/sunnyb.png"));
        background.setBounds(0, 0, 450, 700);
        panel.add(background);
        panel.setComponentZOrder(background, panel.getComponentCount() - 1);

        JLabel widgetBackground = new JLabel();
        widgetBackground.setBounds(30, 580, 370, 90);
        widgetBackground.setLayout(null);
        widgetBackground.setOpaque(false);
        widgetBackground.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150), 3));
        panel.add(widgetBackground);
        panel.setComponentZOrder(widgetBackground, panel.getComponentCount() - 2);



        JTextField searchTextField = new JTextField();

        //can change Font size, style
        searchTextField.setBounds(20, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        searchTextField.setOpaque(false);
        background.add(searchTextField);

        JLabel weatherConditionImage = new JLabel(loadImage("src/img/sunny.png"));
        weatherConditionImage.setBounds(0, 125, 450,217);
        background.add(weatherConditionImage);

        JLabel temperatureText = new JLabel("25 °C");
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

        JLabel nextdayImage = new JLabel(loadImage("src/img/sun1.png"));
        nextdayImage.setBounds(200, 600, 50, 50);
        background.add(nextdayImage);

        JLabel nextdayText = new JLabel("<html><b>Nextday Max</b>");
        nextdayText.setFont(new Font("Dialog", Font.PLAIN, 16));
        nextdayText.setBounds(90, 600, 80, 50);
        background.add(nextdayText);

        JLabel nextdaytemperatureText = new JLabel("<html><b>" + 21 + "°C");
        nextdaytemperatureText.setFont(new Font("Dialog", Font.PLAIN, 16));
        nextdaytemperatureText.setBounds(300, 600, 55, 50);
        background.add(nextdaytemperatureText);



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
                    case "Sunny":
                        background.setIcon(loadImage("src/img/sunnyb.png"));
                        weatherConditionImage.setIcon(loadImage("src/img/sunny.png"));
                        break;
                    case "Cloudy":
                        background.setIcon(loadImage("src/img/cloudb.png"));
                        weatherConditionImage.setIcon(loadImage("src/img/cloud.png"));
                        break;
                    case "Rain":
                        background.setIcon(loadImage("src/img/rainb.png"));
                        weatherConditionImage.setIcon(loadImage("src/img/rain.png"));
                        break;
                    case "Snow":
                        background.setIcon(loadImage("src/img/snowb.png"));
                        weatherConditionImage.setIcon(loadImage("src/img/snow.png"));
                        break;
                }
//              data update
                double temperature = (Double) weatherData.get("temperature");
                temperatureText.setText(temperature + " °C");

                conditionDescriptionText.setText(weatherCondition);

                long humidity = (Long) weatherData.get("humidity");
                HumidityText.setText("<html><b>Humidity</b> "+ humidity +" %</html>");

                double windspeed = (Long) weatherData.get("humidity");
                windspeedText.setText("<html><b>Windspeed</b> "+ windspeed +" km/h</html>");

                String nextDayWeatherCondition = (String) weatherData.get("weather_condition");
                switch (nextDayWeatherCondition) {
                    case "Sunny":
                        nextdayImage.setIcon(loadImage("src/img/sun1.png"));
                        break;
                    case "Cloudy":
                        nextdayImage.setIcon(loadImage("src/img/cloud1.png"));
                        break;
                    case "Rain":
                        nextdayImage.setIcon(loadImage("src/img/rain1.png"));
                        break;
                    case "Snow":
                        nextdayImage.setIcon(loadImage("src/img/snow1.png"));
                        break;
                }
                double nextDayMaxTemp = (Double) weatherData.get("next_day_max_temperature");
                nextdaytemperatureText.setText("<html><b>" + nextDayMaxTemp + "°C</html>");
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

    public void scrollDown() {
        if (scrollPane != null) {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGUI app = new WeatherAppGUI();
            app.setVisible(true);
            app.scrollDown();
        });
    }
}
