package premade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import rapidFX.interfaces.RapidFXComponent;

public class RLanguageManager<KEYS> implements RapidFXComponent
{
	String languageSettings = "";
	Properties props = new Properties();
	URI url;
	File f;
	public RLanguageManager(String languageSettings) throws URISyntaxException
	{
		this.languageSettings = languageSettings;
		url = new URI(getResource(languageSettings));
		f = new File(url);
	}

	public void read() throws IOException, URISyntaxException
	{
		FileReader reader = new FileReader(f);
		props.load(reader);
		System.out.println(props.get("test"));
	}

	public void write() throws IOException
	{
		FileOutputStream writer = new FileOutputStream(languageSettings);
		props.store(writer, null);
	}
	public Object getKeys(KEYS key) {
		return props.get(key);
	}
	
}
