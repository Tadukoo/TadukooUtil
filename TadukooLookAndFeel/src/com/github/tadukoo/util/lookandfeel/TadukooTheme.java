package com.github.tadukoo.util.lookandfeel;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.function.ThrowingFunction4;
import com.github.tadukoo.util.logger.EasyLogger;
import com.github.tadukoo.util.lookandfeel.componentui.TadukooButtonUI;
import com.github.tadukoo.util.lookandfeel.paintui.ColorPaintUIResource;
import com.github.tadukoo.util.lookandfeel.paintui.PaintUIResource;
import com.github.tadukoo.util.view.ShapeFunction;
import com.github.tadukoo.util.view.Shapes;
import com.github.tadukoo.util.view.font.FontFamilies;
import com.github.tadukoo.util.view.font.FontFamily;
import com.github.tadukoo.util.view.font.FontResourceLoader;

import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Theme class for {@link TadukooLookAndFeel}. You can use the {@link TadukooThemeBuilder builder} via the
 * {@link #builder()} method to construct it and specify whatever customizations you want.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooTheme{
	
	/**
	 * Builder for {@link TadukooTheme}. There are no required fields - all of them will be
	 * defaulted based on the default Tadukoo Theme. The following fields may be specified:
	 * <br><br>
	 *
	 * <b>Component UI Classes</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonUI</td>
	 *         <td>The {@link ButtonUI} class to use</td>
	 *         <td>TadukooButtonUI.class</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <b>Paint Parameters</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFocusPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified focus paints</td>
	 *         <td>new ColorPaintUIResource(Color.YELLOW)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFocusPaint</td>
	 *         <td>The {@link PaintUIResource} to use for focus on Buttons</td>
	 *         <td>null (defaults to the {@code defaultFocusPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultSelectPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified select paints</td>
	 *         <td>new ColorPaintUIResource(Color.RED)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonSelectPaint</td>
	 *         <td>The {@link PaintUIResource} to use for select on Buttons</td>
	 *         <td>null (defaults to the {@code defaultSelectPaint} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <b>Font Parameters</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFont</td>
	 *         <td>The font to use for unspecified fonts - specified as a {@link FontFamily}, font style, and
	 *         font size</td>
	 *         <td>{@link FontFamilies#CALIBRI}, style {@link Font#PLAIN}, and size 12</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFont</td>
	 *         <td>The font to use for Buttons - specified as a {@link FontFamily}, font style, and font size</td>
	 *         <td>null (defaults to the {@code defaultFont} value</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <b>Font Resource Loading Parameters</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *        <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>logFontResourceLoaderWarnings</td>
	 *         <td>Whether to log warnings generated by the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>false</td>
	 *     </tr>
	 *     <tr>
	 *         <td>logger</td>
	 *         <td>An {@link EasyLogger} that will be sent to the FontResourceLoader by default
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>null (since logging warnings is set to false by default)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>graphEnv</td>
	 *         <td>The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>{@link GraphicsEnvironment#getLocalGraphicsEnvironment()}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>fontFolder</td>
	 *         <td>The path to the fonts folder to find font files in if needed in the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>"fonts/"</td>
	 *     </tr>
	 *     <tr>
	 *         <td>fontResourceLoader</td>
	 *        <td>The {@link FontResourceLoader} to use in loading fonts and/or ensuring they're in the system</td>
	 *        <td>a new FontResourceLoader with the specified values for {@link #logFontResourceLoaderWarnings},
	 *         {@link #logger}, {@link #graphEnv}, and {@link #fontFolder}</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <b>Shape Parameters</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultShapeFunc</td>
	 *         <td>The {@link ShapeFunction} to use for all unspecified shapes</td>
	 *         <td>{@link Shapes#RECTANGLE_WITH_CUT_CORNERS_TR_BL}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonShapeFunc</td>
	 *         <td>The {@link ShapeFunction} to use for Buttons</td>
	 *         <td>null (defaults to the {@code defaultShapeFunc} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <b>Border Parameters</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultBorder</td>
	 *         <td>The {@link Border} to use for all unspecified borders</td>
	 *         <td>new TadukooBorder()</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonBorder</td>
	 *         <td>The {@link Border} to use on Buttons</td>
	 *         <td>null (defaults to the {@code defaultBorder} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <b>Other Customizations</b>
	 * <table>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>classDefaults</td>
	 *         <td>Class defaults beyond those specified in the "Component UI Classes" section</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 *     <tr>
	 *         <td>systemColorDefaults</td>
	 *         <td>System Color defaults</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 *     <tr>
	 *         <td>componentDefaults</td>
	 *         <td>Component defaults beyond those specified in the above sections</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class TadukooThemeBuilder{
		/*
		 * Component UIs
		 */
		
		/** The {@link ButtonUI} class to use */
		private Class<? extends ButtonUI> buttonUI = TadukooButtonUI.class;
		
		/*
		 * Colors
		 */
		
		// TODO: Set (actual) defaults for colors
		
		// Focus Colors
		/** The {@link PaintUIResource} to use for all unspecified focus paints */
		private PaintUIResource defaultFocusPaint = new ColorPaintUIResource(Color.YELLOW);
		/** The {@link PaintUIResource} to use for focus on Buttons */
		private PaintUIResource buttonFocusPaint = null;
		
		// Select Colors
		/** The {@link PaintUIResource} to use for all unspecified select paints */
		private PaintUIResource defaultSelectPaint = new ColorPaintUIResource(Color.RED);
		/** The {@link PaintUIResource} to use for select on Buttons */
		private PaintUIResource buttonSelectPaint = null;
		
		/*
		 * Fonts
		 */
		
		// Default Font
		/** The {@link FontFamily} to use for unspecified fonts */
		private FontFamily defaultFontFamily = FontFamilies.CALIBRI.getFamily();
		/** The font style to use for unspecified fonts */
		private int defaultFontStyle = Font.PLAIN;
		/** The font size to use for unspecified fonts */
		private int defaultFontSize = 12;
		
		// Button Font
		/** The {@link FontFamily} to use for Buttons */
		private FontFamily buttonFontFamily = null;
		/** The Font style to use for Buttons */
		private int buttonFontStyle = -1;
		/** The font size to use for Buttons */
		private int buttonFontSize = -1;
		
		/*
		 * Font Resource Loading
		 */
		
		/** Whether to log warnings generated by the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private boolean logFontResourceLoaderWarnings = false;
		/** An {@link EasyLogger} that will be sent to the FontResourceLoader by default
		 *  - can be ignored if you specify your own FontResourceLoader */
		private EasyLogger logger = null;
		/** The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		/** The path to the fonts folder to find font files in if needed in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private String fontFolder = "fonts/";
		/** The {@link FontResourceLoader} to use in loading fonts and/or ensuring they're in the system */
		private FontResourceLoader fontResourceLoader = null;
		
		/*
		 * Shapes
		 */
		
		/** The {@link ShapeFunction} to use for unspecified shapes */
		private ShapeFunction defaultShapeFunc = Shapes.RECTANGLE_WITH_CUT_CORNERS_TR_BL.getShapeFunc();
		/** The {@link ShapeFunction} to use for Buttons */
		private ShapeFunction buttonShapeFunc = null;
		
		/*
		 * Borders
		 */
		/** The {@link Border} to use for all unspecified borders */
		private Border defaultBorder = new TadukooBorder();
		/** The {@link Border} to use on Buttons */
		private Border buttonBorder = null;
		
		/*
		 * Other Customizations
		 */
		
		/** Class defaults beyond those specified in the "Component UI Classes" section */
		private Map<String, Class<?>> classDefaults = new HashMap<>();
		 /** System Color defaults */
		private Map<String, ColorUIResource> systemColorDefaults = new HashMap<>();
		/** Component defaults beyond those specified in the above sections */
		private Map<String, Object> componentDefaults = new HashMap<>();
		
		// Cannot create TadukooThemeBuilder outside of TadukooTheme
		private TadukooThemeBuilder(){ }
		
		/**
		 * @param buttonUI The {@link ButtonUI} class to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonUI(Class<? extends ButtonUI> buttonUI){
			this.buttonUI = buttonUI;
			return this;
		}
		
		/*
		 * Focus Color Methods
		 */
		
		/**
		 * @param defaultFocusPaint The {@link PaintUIResource} to use for all unspecified focus paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFocusPaint(PaintUIResource defaultFocusPaint){
			this.defaultFocusPaint = defaultFocusPaint;
			return this;
		}
		
		/**
		 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFocusPaint(PaintUIResource buttonFocusPaint){
			this.buttonFocusPaint = buttonFocusPaint;
			return this;
		}
		
		/*
		 * Select Color Methods
		 */
		
		/**
		 * @param defaultSelectPaint The {@link PaintUIResource} to use for all unspecified select paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultSelectPaint(PaintUIResource defaultSelectPaint){
			this.defaultSelectPaint = defaultSelectPaint;
			return this;
		}
		
		/**
		 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonSelectPaint(PaintUIResource buttonSelectPaint){
			this.buttonSelectPaint = buttonSelectPaint;
			return this;
		}
		
		/*
		 * Font Methods
		 */
		
		/**
		 * Specifies the font to use for unspecified fonts
		 *
		 * @param defaultFontFamily The {@link FontFamily} to use
		 * @param defaultFontStyle The font style to use
		 * @param defaultFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFont(FontFamily defaultFontFamily, int defaultFontStyle, int defaultFontSize){
			this.defaultFontFamily = defaultFontFamily;
			this.defaultFontStyle = defaultFontStyle;
			this.defaultFontSize = defaultFontSize;
			return this;
		}
		
		/**
		 * Specifies the font to use for Buttons
		 *
		 * @param buttonFontFamily The {@link FontFamily} to use
		 * @param buttonFontStyle The font style to use
		 * @param buttonFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFont(FontFamily buttonFontFamily, int buttonFontStyle, int buttonFontSize){
			this.buttonFontFamily = buttonFontFamily;
			this.buttonFontStyle = buttonFontStyle;
			this.buttonFontSize = buttonFontSize;
			return this;
		}
		
		/*
		 * Font Resource Loading Methods
		 */
		
		/**
		 * @param logFontResourceLoaderWarnings Whether to log warnings generated by the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder logFontResourceLoaderWarnings(boolean logFontResourceLoaderWarnings){
			this.logFontResourceLoaderWarnings = logFontResourceLoaderWarnings;
			return this;
		}
		
		/**
		 * @param logger An {@link EasyLogger} that will be sent to the FontResourceLoader by default
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder logger(EasyLogger logger){
			this.logger = logger;
			return this;
		}
		
		/**
		 * @param graphEnv The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder graphEnv(GraphicsEnvironment graphEnv){
			this.graphEnv = graphEnv;
			return this;
		}
		
		/**
		 * @param fontFolder The path to the fonts folder to find font files in if needed in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder fontFolder(String fontFolder){
			this.fontFolder = fontFolder;
			return this;
		}
		
		/**
		 * @param fontResourceLoader The {@link FontResourceLoader} to use in loading fonts and/or ensuring
		 *  they're in the system
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder fontResourceLoader(FontResourceLoader fontResourceLoader){
			this.fontResourceLoader = fontResourceLoader;
			return this;
		}
		
		/*
		 * Shape Methods
		 */
		
		/**
		 * @param defaultShapeFunc The {@link ShapeFunction} to use for unspecified shapes
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultShapeFunc(ShapeFunction defaultShapeFunc){
			this.defaultShapeFunc = defaultShapeFunc;
			return this;
		}
		
		/**
		 * @param buttonShapeFunc The {@link ShapeFunction} to use for Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonShapeFunc(ShapeFunction buttonShapeFunc){
			this.buttonShapeFunc = buttonShapeFunc;
			return this;
		}
		
		/*
		 * Border Methods
		 */
		
		/**
		 * @param defaultBorder The {@link Border} to use for all unspecified borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultBorder(Border defaultBorder){
			this.defaultBorder = defaultBorder;
			return this;
		}
		
		/**
		 * @param buttonBorder The {@link Border} to use on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonBorder(Border buttonBorder){
			this.buttonBorder = buttonBorder;
			return this;
		}
		
		/*
		 * Other Customizations
		 */
		
		/**
		 * @param classDefaults Class defaults beyond those specified in the "Component UI Classes" section
		 *                      - this overwrites the map
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder classDefaults(Map<String, Class<?>> classDefaults){
			this.classDefaults = classDefaults;
			return this;
		}
		
		/**
		 * Adds a class default beyond those specified in the "Component UI Classes" section - this
		 * adds to the map instead of overwriting it
		 *
		 * @param key The key for the class default
		 * @param clazz The actual Class for the class default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder classDefault(String key, Class<?> clazz){
			classDefaults.put(key, clazz);
			return this;
		}
		
		/**
		 * @param systemColorDefaults System Color defaults
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder systemColorDefaults(Map<String, ColorUIResource> systemColorDefaults){
			this.systemColorDefaults = systemColorDefaults;
			return this;
		}
		
		/**
		 * Adds a system color default - this adds to the map instead of overwriting it
		 *
		 * @param key The key for the system color default
		 * @param color The actual color for the system color default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder systemColorDefault(String key, ColorUIResource color){
			systemColorDefaults.put(key, color);
			return this;
		}
		
		/**
		 * @param componentDefaults Component defaults beyond those specified in the above sections
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder componentDefaults(Map<String, Object> componentDefaults){
			this.componentDefaults = componentDefaults;
			return this;
		}
		
		/**
		 * Adds a component default beyond those specified in the above sections - this adds to the map instead
		 * of overwriting it
		 *
		 * @param key The key for the component default
		 * @param value The actual value for the component default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder componentDefault(String key, Object value){
			componentDefaults.put(key, value);
			return this;
		}
		
		/**
		 * Checks for any errors in the parameters that were set
		 */
		public void checkForErrors(){
			// TODO: Check for errors
		}
		
		/**
		 * Builds a {@link TadukooTheme} using the given customizations (or default customizations for unspecified
		 * parameters).
		 *
		 * @return A new {@link TadukooTheme}
		 */
		public TadukooTheme build() throws IOException, FontFormatException{
			checkForErrors();
			
			/*
			 * Handle Default Colors
			 */
			if(buttonFocusPaint == null){
				buttonFocusPaint = defaultFocusPaint;
			}
			
			if(buttonSelectPaint == null){
				buttonSelectPaint = defaultSelectPaint;
			}
			
			/*
			 * Handle Default Fonts
			 */
			if(buttonFontFamily == null){
				buttonFontFamily = defaultFontFamily;
				buttonFontStyle = defaultFontStyle;
				buttonFontSize = defaultFontSize;
			}
			
			// Handle font resource loading
			if(fontResourceLoader == null){
				fontResourceLoader = new FontResourceLoader(logFontResourceLoaderWarnings, logger, graphEnv,
						fontFolder);
			}
			
			// Load fonts
			List<FontFamily> fontFamilies = ListUtil.createList(buttonFontFamily);
			List<String> foundFonts = fontResourceLoader.loadFonts(fontFamilies, true);
			
			// Create the FontUIResources
			FontUIResource buttonFont = new FontUIResource(foundFonts.get(0), buttonFontStyle, buttonFontSize);
			
			/*
			 * Handle Default Shapes
			 */
			if(buttonShapeFunc == null){
				buttonShapeFunc = defaultShapeFunc;
			}
			
			/*
			 * Handle Default Borders
			 */
			if(buttonBorder == null){
				buttonBorder = defaultBorder;
			}
			
			/*
			 * Handle Other Customizations
			 */
			
			// Handle Class Defaults
			int classDefaultsNum = classDefaults.keySet().size();
			Object[] classDefaultsArray = new Object[classDefaultsNum*2];
			Iterator<String> classDefaultsIt = classDefaults.keySet().iterator();
			for(int i = 0; i < classDefaultsNum; i++){
				String key = classDefaultsIt.next();
				classDefaultsArray[i*2] = key;
				classDefaultsArray[i*2+1] = classDefaults.get(key).getCanonicalName();
			}
			
			// Handle System Color Defaults
			int systemColorDefaultsNum = systemColorDefaults.keySet().size();
			Object[] systemColorDefaultsArray = new Object[systemColorDefaultsNum*2];
			Iterator<String> systemColorDefaultsIt = systemColorDefaults.keySet().iterator();
			for(int i = 0; i < systemColorDefaultsNum; i++){
				String key = systemColorDefaultsIt.next();
				systemColorDefaultsArray[i*2] = key;
				systemColorDefaultsArray[i*2+1] = systemColorDefaults.get(key);
			}
			
			// Handle Component Defaults
			int componentDefaultsNum = componentDefaults.keySet().size();
			Object[] componentDefaultsArray = new Object[componentDefaultsNum*2];
			Iterator<String> componentDefaultsIt = componentDefaults.keySet().iterator();
			for(int i = 0; i < componentDefaultsNum; i++){
				String key = componentDefaultsIt.next();
				componentDefaultsArray[i*2] = key;
				componentDefaultsArray[i*2+1] = componentDefaults.get(key);
			}
			
			return new TadukooTheme(buttonUI.getCanonicalName(),
					buttonFocusPaint, buttonSelectPaint, buttonFont, buttonShapeFunc, buttonBorder,
					classDefaultsArray, systemColorDefaultsArray, componentDefaultsArray);
		}
	}
	
	/** The {@link ButtonUI} class to use */
	private final String buttonUI;
	/** The {@link PaintUIResource} to use for focus on Buttons */
	private final PaintUIResource buttonFocusPaint;
	/** The {@link PaintUIResource} to use for select on Buttons */
	private final PaintUIResource buttonSelectPaint;
	/** The {@link FontUIResource} to use for Buttons */
	private final FontUIResource buttonFont;
	/** The {@link ShapeFunction} to use on Buttons */
	private final ShapeFunction buttonShapeFunc;
	/** The {@link Border} to use on Buttons */
	private final Border buttonBorder;
	/** Class defaults beyond those specified in the "Component UI Classes" section */
	private final Object[] classDefaults;
	/** System Color defaults */
	private final Object[] systemColorDefaults;
	/** Component defaults beyond those specified in the other sections */
	private final Object[] componentDefaults;
	
	/**
	 * Constructs a new TadukooTheme with the given customizations.
	 *
	 * @param buttonUI The {@link ButtonUI} class to use
	 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
	 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
	 * @param buttonFont The {@link FontUIResource} to use for Buttons
	 * @param buttonShapeFunc The {@link ShapeFunction} to use on Buttons
	 * @param buttonBorder The {@link Border} to use on Buttons
	 * @param classDefaults Class defaults beyond those specified in the "Component UI Classes" section
	 * @param systemColorDefaults System Color defaults
	 * @param componentDefaults Component defaults beyond those specified in the other sections
	 */
	private TadukooTheme(String buttonUI,
	                     PaintUIResource buttonFocusPaint, PaintUIResource buttonSelectPaint, FontUIResource buttonFont,
	                     ShapeFunction buttonShapeFunc, Border buttonBorder,
	                     Object[] classDefaults, Object[] systemColorDefaults, Object[] componentDefaults){
		this.buttonUI = buttonUI;
		this.buttonFocusPaint = buttonFocusPaint;
		this.buttonSelectPaint = buttonSelectPaint;
		this.buttonFont = buttonFont;
		this.buttonShapeFunc = buttonShapeFunc;
		this.buttonBorder = buttonBorder;
		this.classDefaults = classDefaults;
		this.systemColorDefaults = systemColorDefaults;
		this.componentDefaults = componentDefaults;
	}
	
	/**
	 * @return A {@link TadukooThemeBuilder} to use in building a TadukooTheme
	 */
	public static TadukooThemeBuilder builder(){
		return new TadukooThemeBuilder();
	}
	
	/**
	 * @return The {@link ButtonUI} class to use
	 */
	public String getButtonUI(){
		return buttonUI;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for focus on Buttons
	 */
	public PaintUIResource getButtonFocusPaint(){
		return buttonFocusPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for select on Buttons
	 */
	public PaintUIResource getButtonSelectPaint(){
		return buttonSelectPaint;
	}
	
	/**
	 * @return The {@link FontUIResource} to use for Buttons
	 */
	public FontUIResource getButtonFont(){
		return buttonFont;
	}
	
	/**
	 * @return The {@link ShapeFunction} to use on Buttons
	 */
	public ShapeFunction getButtonShapeFunc(){
		return buttonShapeFunc;
	}
	
	/**
	 * @return The {@link Border} to use on Buttons
	 */
	public Border getButtonBorder(){
		return buttonBorder;
	}
	
	/**
	 * @return Class defaults beyond those specified in the "Component UI Classes" section
	 */
	public Object[] getClassDefaults(){
		return classDefaults;
	}
	
	/**
	 * @return System Color defaults
	 */
	public Object[] getSystemColorDefaults(){
		return systemColorDefaults;
	}
	
	/**
	 * @return Component defaults beyond those specified in the other sections
	 */
	public Object[] getComponentDefaults(){
		return componentDefaults;
	}
}
