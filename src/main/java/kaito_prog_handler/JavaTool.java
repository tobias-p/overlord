package kaito_prog_handler;

import lombok.Getter;

public class JavaTool {

	@Getter
	private String name;
	@Getter
	private String folder;
	@Getter
	private String gitPath;

	/** A tool */
	public JavaTool(String name, String folder, String gitPath) {
		this.name = name;
		this.folder = folder;
		this.gitPath = gitPath;
	}
	

}
