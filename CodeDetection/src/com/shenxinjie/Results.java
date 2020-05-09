package com.shenxinjie;
public class Results
{
	private String fileColumnTitle;

	private String resultColumnTitle;

	private String[] orFileNames;

	private String[] fileNames;

	private String[] results;

	private String[][] fileContents;

	private int[] recordX;

	private int[] recordY;

	private int[][] matchR;

	private int[][][][] matchResults;

	private int num;

	public Results(String[] orFileNames, String[] fileNames, String[] results)
	{
		fileColumnTitle = "文件名";
		resultColumnTitle = "相似度";
		this.orFileNames = orFileNames;
		this.fileNames = fileNames;
		this.results = results;
		num = 0;
	}

	public Results(String[] orFileNames, String[] fileNames, String[] results, String[][] fileContents, int num)
	{
		fileColumnTitle = "文件名";
		resultColumnTitle = "相似度";
		this.orFileNames = orFileNames;
		this.fileNames = fileNames;
		this.results = results;
		this.fileContents = fileContents;
		this.num = num;
		recordX = new int[1];
		recordX[0] = 5;
	}

	public Results(String[] orFileNames, String[] fileNames, String[] results, String[][] fileContents, int num, int[] recordX, int[] recordY)
	{
		fileColumnTitle = "文件名";
		resultColumnTitle = "相似度";
		this.orFileNames = orFileNames;
		this.fileNames = fileNames;
		this.results = results;
		this.fileContents = fileContents;
		this.num = num;
		this.recordX = recordX;
		this.recordY = recordY;
	}

	public Results(String[] orFileNames, String[] fileNames, String[] results, String[][] fileContents, int num,
		int[] recordX, int[] recordY, int[][] matchR, int[][][][] matchResults)
	{
		fileColumnTitle = "文件名";
		resultColumnTitle = "相似度";
		this.orFileNames = orFileNames;
		this.fileNames = fileNames;
		this.results = results;
		this.fileContents = fileContents;
		this.num = num;
		this.recordX = recordX;
		this.recordY = recordY;
		this.matchR = matchR;
		this.matchResults = matchResults;
	}


	public void setOrFileNames(String[] names)
	{
		orFileNames = names;
	}

	public void setFileNames(String[] names)
	{
		fileNames = names;
	}

	public void setResults(String[] results)
	{
		this.results = results;
	}

	public String[] getOrFileNames()
	{
		return orFileNames;
	}

	public String[] getFileNames()
	{
		return fileNames;
	}

	public String[] getResults()
	{
		return results;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public int getNum()
	{
		return num;
	}

	public void setFileContents(String[][] fileContents)
	{
		this.fileContents = fileContents;
	}

	public String[][] getFileContents()
	{
		return fileContents;
	}

	public void setMatchR(int[][] matchR)
	{
		this.matchR = matchR;
	}

	public int[][] getMatchR()
	{
		return matchR;
	}

	public void setMatchResults(int[][][][] matchResults)
	{
		this.matchResults = matchResults;
	}

	public int[][][][] getMatchResults()
	{
		return matchResults;
	}

	public void setRecordX(int[] recordX)
	{
		this.recordX = recordX;
	}

	public int[] getRecordX()
	{
		return recordX;
	}

	public void setRecordY(int[] recordX)
	{
		this.recordY = recordY;
	}

	public int[] getRecordY()
	{
		return recordY;
	}

}
