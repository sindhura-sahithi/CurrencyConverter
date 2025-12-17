import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

class Currency {
    private final String name;
    private final String shortName;
    private final Map<String, Double> exchangeValues = new HashMap<>();

    public Currency(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
        setDefaultExchangeValues();
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Double convert(double amount, String toCurrency) {
        return exchangeValues.containsKey(toCurrency) ? amount * exchangeValues.get(toCurrency) : null;
    }

    private void setDefaultExchangeValues() {
        switch (name) {
            case "US Dollar":
                exchangeValues.put("USD", 1.00);
                exchangeValues.put("EUR", 0.93);
                exchangeValues.put("GBP", 0.66);
                exchangeValues.put("CHF", 1.01);
                exchangeValues.put("CNY", 6.36);
                exchangeValues.put("JPY", 123.54);
                exchangeValues.put("INR", 86.30);
                break;
            case "Euro":
                exchangeValues.put("USD", 1.073);
                exchangeValues.put("EUR", 1.00);
                exchangeValues.put("GBP", 0.71);
                exchangeValues.put("CHF", 1.08);
                exchangeValues.put("CNY", 6.83);
                exchangeValues.put("JPY", 132.57);
                exchangeValues.put("INR", 92.94);
                break;
            case "British Pound":
                exchangeValues.put("USD", 1.51);
                exchangeValues.put("EUR", 1.41);
                exchangeValues.put("GBP", 1.00);
                exchangeValues.put("CHF", 1.52);
                exchangeValues.put("CNY", 9.60);
                exchangeValues.put("JPY", 186.41);
                exchangeValues.put("INR", 122.25);
                break;
            case "Swiss Franc":
                exchangeValues.put("USD", 0.99);
                exchangeValues.put("EUR", 0.93);
                exchangeValues.put("GBP", 0.66);
                exchangeValues.put("CHF", 1.00);
                exchangeValues.put("CNY", 6.33);
                exchangeValues.put("JPY", 122.84);
                exchangeValues.put("INR", 79.97);
                break;
            case "Chinese Yuan Renminbi":
                exchangeValues.put("USD", 0.16);
                exchangeValues.put("EUR", 0.15);
                exchangeValues.put("GBP", 0.11);
                exchangeValues.put("CHF", 0.16);
                exchangeValues.put("CNY", 1.00);
                exchangeValues.put("JPY", 19.41);
                exchangeValues.put("INR", 13.07);
                break;
            case "Japanese Yen":
                exchangeValues.put("USD", 0.008);
                exchangeValues.put("EUR", 0.007);
                exchangeValues.put("GBP", 0.005);
                exchangeValues.put("CHF", 0.008);
                exchangeValues.put("CNY", 0.051);
                exchangeValues.put("JPY", 1.00);
                exchangeValues.put("INR", 0.64);
                break;
            case "Indian Rupee":
                exchangeValues.put("USD", 0.012);
                exchangeValues.put("EUR", 0.011);
                exchangeValues.put("GBP", 0.008);
                exchangeValues.put("CHF", 0.012);
                exchangeValues.put("CNY", 0.076);
                exchangeValues.put("JPY", 1.56);
                exchangeValues.put("INR", 1.00);
                break;
        }
    }

    public static List<Currency> init() {
        return List.of(
                new Currency("US Dollar", "USD"),
                new Currency("Euro", "EUR"),
                new Currency("British Pound", "GBP"),
                new Currency("Swiss Franc", "CHF"),
                new Currency("Chinese Yuan Renminbi", "CNY"),
                new Currency("Japanese Yen", "JPY"),
                new Currency("Indian Rupee", "INR")
        );
    }
}

public class CurrencyConverterGUIFinal {
    private static final List<Currency> CURRENCIES = Currency.init();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Currency Converter");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            frame.add(mainPanel);

            // Heading panel
            JPanel headingPanel = new JPanel();
            JLabel headingLabel = new JLabel("CURRENCY CONVERTER", SwingConstants.CENTER);
            headingLabel.setFont(new Font("Verdana", Font.BOLD, 24));
            headingPanel.add(headingLabel);
            mainPanel.add(headingPanel, BorderLayout.NORTH);

            // Component panel with better layout
            JPanel componentPanel = new JPanel(new GridBagLayout());
            componentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            Font customFont = new Font("Verdana", Font.BOLD, 16);

            // Amount input
            gbc.gridx = 0; gbc.gridy = 0;
            JLabel amountLabel = new JLabel("Enter Amount:");
            amountLabel.setFont(customFont);
            componentPanel.add(amountLabel, gbc);

            gbc.gridx = 1;
            JTextField amountField = new JTextField(15);
            amountField.setFont(customFont);
            componentPanel.add(amountField, gbc);

            // From currency
            gbc.gridx = 0; gbc.gridy = 1;
            JLabel fromLabel = new JLabel("From Currency:");
            fromLabel.setFont(customFont);
            componentPanel.add(fromLabel, gbc);

            gbc.gridx = 1;
            JComboBox<String> fromCurrencyComboBox = new JComboBox<>();
            fromCurrencyComboBox.setFont(customFont);
            fromCurrencyComboBox.setPreferredSize(new Dimension(200, 30));
            CURRENCIES.forEach(currency -> fromCurrencyComboBox.addItem(currency.getName()));
            componentPanel.add(fromCurrencyComboBox, gbc);

            // To currency
            gbc.gridx = 0; gbc.gridy = 2;
            JLabel toLabel = new JLabel("To Currency:");
            toLabel.setFont(customFont);
            componentPanel.add(toLabel, gbc);

            gbc.gridx = 1;
            JComboBox<String> toCurrencyComboBox = new JComboBox<>();
            toCurrencyComboBox.setFont(customFont);
            toCurrencyComboBox.setPreferredSize(new Dimension(200, 30));
            CURRENCIES.forEach(currency -> toCurrencyComboBox.addItem(currency.getName()));
            componentPanel.add(toCurrencyComboBox, gbc);

            // Convert button and result
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton convertButton = new JButton("Convert");
            convertButton.setFont(customFont);
            componentPanel.add(convertButton, gbc);

            gbc.gridy = 4;
            JLabel resultLabel = new JLabel("Result: ");
            resultLabel.setFont(customFont);
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            componentPanel.add(resultLabel, gbc);

            // Action listener
            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double amount = Double.parseDouble(amountField.getText().trim());
                        String fromCurrencyName = (String) fromCurrencyComboBox.getSelectedItem();
                        String toCurrencyName = (String) toCurrencyComboBox.getSelectedItem();

                        Currency fromCurrency = CURRENCIES.stream()
                                .filter(c -> c.getName().equals(fromCurrencyName))
                                .findFirst().orElse(null);
                        Currency toCurrency = CURRENCIES.stream()
                                .filter(c -> c.getName().equals(toCurrencyName))
                                .findFirst().orElse(null);

                        if (fromCurrency != null && toCurrency != null) {
                            Double convertedAmount = fromCurrency.convert(amount, toCurrency.getShortName());
                            if (convertedAmount != null) {
                                resultLabel.setText("Result: " + String.format("%.2f %s", convertedAmount, toCurrency.getShortName()));
                            } else {
                                resultLabel.setText("Error: Conversion rate not available.");
                            }
                        } else {
                            resultLabel.setText("Error: Invalid currency selection.");
                        }
                    } catch (NumberFormatException ex) {
                        resultLabel.setText("Error: Please enter a valid number.");
                    }
                }
            });

            mainPanel.add(componentPanel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
