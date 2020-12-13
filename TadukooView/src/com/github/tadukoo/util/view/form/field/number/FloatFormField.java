package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.Form;
import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.FormField;
import com.github.tadukoo.util.view.form.field.LabelType;

/**
 * A Float Form Field is a {@link FormField} used to store floats.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public class FloatFormField extends NumberFormField<Float>{
	
	/**
	 * Builder to be used to create an {@link FloatFormField}. It has the following parameters:
	 *
	 * <table>
	 *     <caption>FormField Parameters</caption>
	 *     <tr>
	 *         <th>Name</th>
	 *         <th>Description</th>
	 *         <th>Default Value or Required</th>
	 *     </tr>
	 *     <tr>
	 *         <td>key</td>
	 *         <td>The name of the field (used as a key in {@link Form Forms})</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultValue</td>
	 *         <td>The starting value of the field</td>
	 *         <td>Defaults to 0</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelType</td>
	 *         <td>The {@link LabelType} to use for the field</td>
	 *         <td>Defaults to {@link LabelType#LABEL}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>rowPos</td>
	 *         <td>The row position of the field</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>colPos</td>
	 *         <td>The column position of the field</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>rowSpan</td>
	 *         <td>The row span of the field</td>
	 *         <td>Defaults to 1</td>
	 *     </tr>
	 *     <tr>
	 *         <td>colSpan</td>
	 *         <td>The column span of the field</td>
	 *         <td>Defaults to 1</td>
	 *     </tr>
	 *     <tr>
	 *         <td>minValue</td>
	 *         <td>The minimum value allowed for the field</td>
	 *         <td>Defaults to null (no minimum)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>maxValue</td>
	 *         <td>The maximum value allowed for the field</td>
	 *         <td>Defaults to null (no maximum)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>stepSize</td>
	 *         <td>The increment value for the spinner for the field</td>
	 *         <td>Defaults to 1.0</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2.1
	 */
	public static class FloatFormFieldBuilder extends NumberFormFieldBuilder<Float>{
		
		// Can't create outside of FloatFormField
		private FloatFormFieldBuilder(){
			super();
			defaultValue = 0.0f;
			stepSize = 1.0f;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder defaultValue(Float defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder minValue(Float minValue){
			super.minValue(minValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder maxValue(Float maxValue){
			super.maxValue(maxValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormFieldBuilder stepSize(Float stepSize){
			super.stepSize(stepSize);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FloatFormField build(){
			return new FloatFormField(key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan,
					minValue, maxValue, stepSize);
		}
	}
	
	/**
	 * Creates a new FloatFormField with the given parameters.
	 *
	 * @param key The name of this field (used as a key in {@link Form Forms})
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 * @param minValue The minimum value allowed for this field
	 * @param maxValue The maximum value allowed for this field
	 * @param stepSize The increment value for the spinner for this field
	 */
	private FloatFormField(String key, Float defaultValue, LabelType labelType,
	                        int rowPos, int colPos, int rowSpan, int colSpan,
	                        Float minValue, Float maxValue, Float stepSize){
		super(FieldType.FLOAT, key, defaultValue, labelType,
				rowPos, colPos, rowSpan, colSpan,
				minValue, maxValue, stepSize);
	}
	
	/**
	 * @return A new {@link FloatFormFieldBuilder} to use to make a {@link FloatFormField}
	 */
	public static FloatFormFieldBuilder builder(){
		return new FloatFormFieldBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	protected Float convertToType(Number number){
		return number.floatValue();
	}
}