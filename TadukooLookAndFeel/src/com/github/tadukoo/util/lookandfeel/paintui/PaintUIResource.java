package com.github.tadukoo.util.lookandfeel.paintui;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.util.List;

public interface PaintUIResource extends UIResource{
	
	/**
	 * Creates a Paint to be used based on the size of the object to be painted. The size is given because in the
	 * case of Gradients, it determines where the points are placed.
	 *
	 * @param size The Dimensions of the object to be painted
	 * @return A Paint
	 */
	Paint getPaint(Dimension size);
	
	/**
	 * In some cases, we need to return a ColorUIResource to support the Look & Feel properly. I don't like it,
	 * but it needs to be done for those Look & Feel pieces to work, and I'm trying to support all of them.
	 *
	 * @return A ColorUIResource
	 */
	ColorUIResource getColorUIResource();
	
	/**
	 * Metal Look and Feel has a trash way of handling gradients. Basically it takes 3 colors and 2 fractions, but
	 * with the 3 colors, it repeats color 1 and does 1, 2, 1, 3. The fractions represent the 2 middle points,
	 * but the 2nd point is calculated as mid1 * 2 + mid2.
	 * <br><br>
	 * This method exists for the purpose of appeasing the Metal Look & Feel god who decided this disgusting way of
	 * code.
	 *
	 * @return The garbage List that Metal Look and Feel expects for gradients that matches best to the one defined here
	 *
	 * @deprecated Forever, because I hate how Metal Look and Feel does this, but I'm trying to support it all
	 */
	@Deprecated
	List<Object> getMetalGradientList();
}