package com.hcss.utill;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {
	public static Logger logger;
	public LoggerManager()
	{
	}
	public Logger getLogger(String aFilePath)
	{
		String aLogDir = aFilePath.substring(0, aFilePath.lastIndexOf("/"));
		logger = Logger.getLogger("Logger");
		try
		{
			File aFile = new File( aLogDir );
			boolean success = aFile.exists();

			if (!success)
				success = aFile.mkdir();

			LogManager lm = LogManager.getLogManager();
			FileHandler fh = new FileHandler(aFilePath, true);
			logger = Logger.getLogger("LoggerManager");
			logger.setUseParentHandlers(false);
			lm.addLogger(logger);
			logger.setLevel(Level.INFO);
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
			//fh.close();
		}
		catch (Exception e)
		{

			logger.log(Level.INFO, e.toString(), e.fillInStackTrace());
		}
		return logger;
	}

	public static void writeLogInfo(Exception e)
	{
		logger.log(Level.INFO, e.toString(), e.fillInStackTrace());
	}

	public static void writeLogSevere(Exception e)
	{
		logger.log(Level.SEVERE, e.toString(), e.fillInStackTrace());
	}

	public static void writeLogWarning(Exception e)
	{
		logger.log(Level.WARNING, e.toString(), e.fillInStackTrace());
	}

	public static void writeLogInfo(String info)
	{
		logger.log(Level.INFO, info);
	}

	public static void writeLogSevere(String severe)
	{
		logger.log(Level.SEVERE, severe);
	}

	public static void writeLogWarning(String warning)
	{
		logger.log(Level.WARNING, warning);
	}

}
