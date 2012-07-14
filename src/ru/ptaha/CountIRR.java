package ru.ptaha;

public class CountIRR {

		
    private float Cred;
    private int PeriodCredit;
    private float Percent;
    private float ComissionRightAway;
    private float ComissionMonth;
    private float ComissionQuarter;
    private float ComissionYear;

	    
    public 	CountIRR(float Cred, int PeriodCredit, float Percent, float ComissionRightAway, float ComissionMonth, float ComissionQuarter, float ComissionYear)
    {
    	/**
    	 * Это конструктор <br>
    	 * @param Cred - вносится размер кредита <br>
    	 * @param PeriodCredit - вносится срок кредита в месяцах <br>
    	 * @param Percent - вносится годовая процентная ставка <br>
    	 * @param ComissionRightAway - вносится начальная плата за кредит <br>
    	 * @param ComissionMonth - вносится ежемесячная плата(комиссия) <br>
    	 * @param ComissionQuarter - вносится ежеквартальная плата(комиссия) <br>
    	 * @param ComissionYear - вносится ежегодная плата(комиссия) <br>
    	 */
    	
    	this.Cred = Cred;
        this.PeriodCredit = PeriodCredit;
        this.Percent = Percent;
        this.ComissionRightAway = ComissionRightAway;
        this.ComissionMonth = ComissionMonth;
        this.ComissionQuarter = ComissionQuarter;
        this.ComissionYear = ComissionYear;
    }
    
    public float count()
    {
    	/**
    	 * @return IRR
    	 */
    	//Расчёт денежного потока, заносим его в массив
        float DP[] = new float [this.PeriodCredit + 1];
        DP[0] = -this.Cred + this.ComissionRightAway;
        float MonthPercent = this.Percent/12/100;
        float MonthPayment;
        if (Math.pow(this.Percent, 2) < 0.000001)
        {
            MonthPayment = 0;
        }
        else
        {
            MonthPayment = (float) (this.Cred*MonthPercent*Math.pow((1 + MonthPercent),
            		this.PeriodCredit)/(Math.pow((1 + MonthPercent), this.PeriodCredit) - 1));
        }

        for (int i = 1; i<=this.PeriodCredit; i++)
        {
            DP[i] = MonthPayment + this.ComissionMonth;
        }

        for (int i=1; i<=this.PeriodCredit; i+=3)
        {
            DP[i] = DP[i] + this.ComissionQuarter;
        }

        for (int i=1; i<=this.PeriodCredit; i+=12)
        {
            DP[i] = DP[i] + this.ComissionYear;
        }

    //------------------------------------------------------------

        /*for (int i = 0; i<=this.PeriodCredit; i++)
        {
            System.out.println(i+") "+DP[i]+'\n');
        }*/


    //---------------------------------------------------------------
    //Расчёт эквивалентной ставки
    //Используем метод дихотомии


        float Up;
        float Down;
        
        Up = 100;
        Down = 0;

        int k = 0;
        float NPV = 1;

        while ((Math.pow(NPV,2)>0.000000000001) && (k<500))
        {
            k++;
            float SummaPayment = 0;

            for (int i = 1; i<=this.PeriodCredit; i++)
            {
                SummaPayment = (float) (SummaPayment + DP[i]/Math.pow((1+(Up + Down)/200), i));
            }

            NPV = DP[0] + SummaPayment;

            if (NPV == 0.0)
            {
                break;
            }

            if (NPV>0.0)
            {
                Down = (Up + Down)/2;
            }
            else
            {
                Up = (Up + Down)/2;
            }
            
        }
    //---------------------------------------------------

        
        //System.out.println('\n'+"IRR "+((Up + Down)/2));
        //std::cout << '\n' << "IRR  " <<(irr.Up + irr.Down)/2;

        //12 потому что Дау Сказал что Расчетный Период это Год
        return 12*(Up + Down)/2;
    }

	

}
