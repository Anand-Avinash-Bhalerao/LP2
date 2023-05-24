public class currencyConverter {
    public String currency1 {get;set;}
    public String currency2 {get;set;}
    public double value1 {get;set;}
    public double value2 {get;set;}
    
    public void convert() {
        if(currency1 == currency2) {
            value2 = value1;
        }
        else if(currency1 == 'Rupee' && currency2 == 'Dollar') {
            value2 = value1/(82.23);
        }
        else if(currency1 == 'Dollar' && currency2 == 'Rupee') {
            value2 = 82.23*(value1);
        }
        else if(currency1 == 'Pound' && currency2 == 'Rupee') {
            value2 = 101.68*(value1);
        }
        else if(currency1 == 'Rupee' && currency2 == 'Pound') {
            value2 = value1/(101.68);
        }
        else if(currency1 == 'Dollar' && currency2 == 'Pound') {
            value2 = value1/(1.24);
        }
        else if(currency1 == 'Pound' && currency2 == 'Dollar') {
            value2 = 1.24*(value1);
        }
    }
}