/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.greenrobot.daogenerator;

/**
 * Generates entities and DAOs for the CrowdSourcing App.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Jessy Lee <jessy1092@gmail.com>
 */
public class CSDaoGenerator
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Schema schema = new Schema(1, "com.openeatsCS.app.model");
		
		addTable(schema);
		try
		{
			new DaoGenerator().generateAll(schema, "C:/Users/Lee/OpenEats/OpeneatsCrowdsourcingApp/src-gen");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void addTable(Schema schema)
	{
		
		Entity barcode = schema.addEntity("Barcode");
		barcode.addIdProperty();
		barcode.addStringProperty("barcode").unique();
		barcode.addStringProperty("name").unique();
		barcode.addStringProperty("loc_photo1");
		barcode.addStringProperty("loc_photo2");
		barcode.addStringProperty("loc_photo3");
		barcode.addBooleanProperty("upload");			// Upload or not
		barcode.addBooleanProperty("finish");			// Need to upload or not
		barcode.addBooleanProperty("update");			// update or not
		
		Entity history = schema.addEntity("History");
		history.addIdProperty();
		history.addDateProperty("time");			// history time
		history.addStringProperty("log");			// log
		Property barcode_id = history.addLongProperty("barcode_id").notNull().getProperty();
		
		// Control Relation manually.
		barcode.addToMany(history, barcode_id);
		history.addToOne(barcode, barcode_id);
	}

}
