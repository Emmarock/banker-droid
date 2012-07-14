package ru.totem3006.dataSaver;


public class IrrRecord implements Comparable<IrrRecord>
{
	public String bank_name;
	public String credit_name;
	public String comment;
	public float irr;
	
	
	public IrrRecord(String bankName, String creditName, String comment, float irr)
	{
		this.bank_name = bankName;
		this.credit_name = creditName;
		this.comment = comment;
		this.irr = irr;
	}

	public int compareTo(IrrRecord another) {
		if (this.irr < another.irr)
		{
			return 1;
		}
		if (this.irr > another.irr)
		{
			return -1;
		}
		return 0;
	}


	
	public String toOutput()
	{
		String result = "";
		result += "IRR:\t" + String.valueOf(this.irr) + '\n';
		result += "Bank name:\t\'" + this.bank_name +"\'" +  '\n';
		result += "Credit name:\t\'" + this.credit_name +"\'" +  '\n';
		result += "Comment: " + this.comment;
		
		return result;
	}
}
