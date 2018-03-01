package com.cpm.xmlHandler;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.cpm.geotagging.GeoTagCityGetterSetter;
import com.cpm.geotagging.GeoTagGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.AssetsGetterSetter;
import com.cpm.xmlGetterSetter.AssetsTargetGetterSetter;
import com.cpm.xmlGetterSetter.BrandMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryVerticalMappingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyMasterGetterSetter;
import com.cpm.xmlGetterSetter.CompetitorGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.KpiReportGetterSetter;
import com.cpm.xmlGetterSetter.LoginGetterSetter;
import com.cpm.xmlGetterSetter.NonSkuGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingGetterSetter;
import com.cpm.xmlGetterSetter.PosmMasterGetterSetter;
import com.cpm.xmlGetterSetter.PromotionGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterDataGetterSetter;
import com.cpm.xmlGetterSetter.SkuTargetGetterSetter;
import com.cpm.xmlGetterSetter.SkufocusGetterSetter;
import com.cpm.xmlGetterSetter.TargetBrandWiseGetterSetter;
import com.cpm.xmlGetterSetter.TargetKPIGetterSetter;
import com.cpm.xmlGetterSetter.VerticalMasterGetterSetter;

public class XMLHandlers {

	// LOGIN XML HANDLER
	public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
			int eventType) {
		LoginGetterSetter lgs = new LoginGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("RESULT")) {
						lgs.setResult(xpp.nextText());
					}
					if (xpp.getName().equals("APP_VERSION")) {
						lgs.setVERSION(xpp.nextText());
					}
					if (xpp.getName().equals("APP_PATH")) {
						lgs.setPATH(xpp.nextText());
					}
					if (xpp.getName().equals("DATE")) {
						lgs.setDATE(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lgs;
	}

	// FAILURE XML HANDLER
	public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
			int eventType) {
		FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STATUS")) {
						failureGetterSetter.setStatus(xpp.nextText());
					}
					if (xpp.getName().equals("ERRORMSG")) {
						failureGetterSetter.setErrorMsg(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return failureGetterSetter;
	}

	// JCP XML HANDLER
	public static JCPGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
		JCPGetterSetter jcpGetterSetter = new JCPGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_ID")) {
						jcpGetterSetter.setStoreid(xpp.nextText());
					}
					if (xpp.getName().equals("VISIT_DATE")) {
						jcpGetterSetter.setVisitdate(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_NAME")) {
						jcpGetterSetter.setStorename(xpp.nextText());
					}
					if (xpp.getName().equals("ADDRESS")) {
						jcpGetterSetter.setStoreaddres(xpp.nextText());
					}
					if (xpp.getName().equals("LATITUDE")) {
						jcpGetterSetter.setStorelatitude(xpp.nextText());
					}
					if (xpp.getName().equals("LOGITUDE")) {
						jcpGetterSetter.setStorelongitude(xpp.nextText());
					}
					if (xpp.getName().equals("STATUS")) {
						jcpGetterSetter.setStatus(xpp.nextText());
					}
					if (xpp.getName().equals("CHECKOUT_STATUS")) {
						jcpGetterSetter.setCheckout_status(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_ID")) {
						jcpGetterSetter.setCATEGORY_ID(xpp.nextText());
					}
					if (xpp.getName().equals("COL1")) {
						jcpGetterSetter.setStatusStore(xpp.nextText());
						
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// NON WORKING RESAON

	public static NonWorkingGetterSetter SubNonWorkingXMLHandler(
			XmlPullParser xpp, int eventType) {
		NonWorkingGetterSetter jcpGetterSetter = new NonWorkingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("REASON_ID")) {
						jcpGetterSetter.setReason_id(xpp.nextText());
					}
					if (xpp.getName().equals("REASON")) {
						jcpGetterSetter.setReason(xpp.nextText());
					}
					if (xpp.getName().equals("ENTRY_ALLOW")) {
						jcpGetterSetter.setEntry(xpp.nextText());
					}
					if (xpp.getName().equals("IMAGE_ALLOW")) {
						jcpGetterSetter.setImage(xpp.nextText());
					}
					if (xpp.getName().equals("MREASON_ID")) {
						jcpGetterSetter.setMreason_id(xpp.nextText());
					}
					if (xpp.getName().equals("INFORMED_TO")) {
						jcpGetterSetter.setInformedto(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_INFO")) {
						jcpGetterSetter.setStoreinfo(xpp.nextText());
					}if (xpp.getName().equals("REMARK")) {
						jcpGetterSetter.setOther(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	public static NonWorkingGetterSetter NonWorkingXMLHandler(
			XmlPullParser xpp, int eventType) {
		NonWorkingGetterSetter jcpGetterSetter = new NonWorkingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("MREASON_ID")) {
						jcpGetterSetter.setMreason_id(xpp.nextText());
					}
					if (xpp.getName().equals("MREASON")) {
						jcpGetterSetter.setMreason(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	public static NonWorkingGetterSetter KeyAcuntXMLHandler(
			XmlPullParser xpp, int eventType) {
		NonWorkingGetterSetter jcpGetterSetter = new NonWorkingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("KEY_ID")) {
						jcpGetterSetter.setKeyid(xpp.nextText());
					}
					if (xpp.getName().equals("KEYACCOUNT")) {
						jcpGetterSetter.setKeyacunt(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// ASSTES MASTER
	public static AssetsGetterSetter AssetsXMLHandler(XmlPullParser xpp,
			int eventType) {
		AssetsGetterSetter jcpGetterSetter = new AssetsGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("ASSET_ID")) {
						jcpGetterSetter.setASSET_ID(xpp.nextText());
					}
					if (xpp.getName().equals("ASSET")) {
						jcpGetterSetter.setASSET(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// ASSTES TARGET
	public static AssetsTargetGetterSetter AssetsTargetXMLHandler(
			XmlPullParser xpp, int eventType) {
		AssetsTargetGetterSetter jcpGetterSetter = new AssetsTargetGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("ASSET_ID")) {
						jcpGetterSetter.setASSET_ID(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_ID")) {
						jcpGetterSetter.setSTORE_ID(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_ID")) {
						jcpGetterSetter.setSKU_ID(xpp.nextText());
					}
					if (xpp.getName().equals("TARGET_QTY")) {
						jcpGetterSetter.setTARGETQTY(xpp.nextText());
					}
					if (xpp.getName().equals("VERTICAL_ID")) {
						jcpGetterSetter.setVerticalid(xpp.nextText());
					}
					
					if (xpp.getName().equals("BRAND")) {
						jcpGetterSetter.setBRAND_NAME(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_ID")) {
						jcpGetterSetter.setBRAND_ID(xpp.nextText());
					}
					
					if (xpp.getName().equals("visi_pure1")) {
						jcpGetterSetter.setASSET_PURE(xpp.nextText());
					}
					

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// VERTICAL MASTER

	public static VerticalMasterGetterSetter VerticalMasterXMLHandler(
			XmlPullParser xpp, int eventType) {
		VerticalMasterGetterSetter jcpGetterSetter = new VerticalMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("VERTICAL_ID")) {
						jcpGetterSetter.setVerticalid(xpp.nextText());
					}
					if (xpp.getName().equals("VERTICAL_NAME")) {
						jcpGetterSetter.setVertical_name(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// BRAND MASTER
	public static BrandMasterGetterSetter BrandMasterXMLHandler(
			XmlPullParser xpp, int eventType) {
		BrandMasterGetterSetter jcpGetterSetter = new BrandMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("BRAND_ID")) {
						jcpGetterSetter.setBrandid(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_NAME")) {
						jcpGetterSetter.setBrand(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_ID")) {
						jcpGetterSetter.setCategoryid(xpp.nextText());
					}
					if (xpp.getName().equals("BR_SEQUENCE")) {
						jcpGetterSetter.setBRANDSEQ(xpp.nextText());
					}
					if (xpp.getName().equals("COMPANY_ID")) {
						jcpGetterSetter.setCompany_id(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// category master
	public static CategoryMasterGetterSetter CategoryMasterXMLHandler(
			XmlPullParser xpp, int eventType) {
		CategoryMasterGetterSetter jcpGetterSetter = new CategoryMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("CATEGORY_ID")) {
						jcpGetterSetter.setCategory_id(xpp.nextText());
					}
					if (xpp.getName().equals("CODE")) {
						jcpGetterSetter.setCODE(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_NAME")) {
						jcpGetterSetter.setCategory_name(xpp.nextText());
					}
					if (xpp.getName().equals("CAT_SEQUENCE")) {
						jcpGetterSetter.setCategory_seq(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// category verical MAPPING
	public static CategoryVerticalMappingGetterSetter CategoryVerticalMappingXMLHandler(
			XmlPullParser xpp, int eventType) {
		CategoryVerticalMappingGetterSetter jcpGetterSetter = new CategoryVerticalMappingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("CATEGORY_ID")) {
						jcpGetterSetter.setCategory_id(xpp.nextText());
					}
					if (xpp.getName().equals("VERTICAL_ID")) {
						jcpGetterSetter.setVertical_id(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_NAME")) {
						jcpGetterSetter.setCategory_name(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// posm MASTER
	public static PosmMasterGetterSetter PosmMasterXMLHandler(
			XmlPullParser xpp, int eventType) {
		PosmMasterGetterSetter jcpGetterSetter = new PosmMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("POSM_ID")) {
						jcpGetterSetter.setPosmid(xpp.nextText());
					}
					if (xpp.getName().equals("POSM")) {
						jcpGetterSetter.setPosmname(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// SKU MASTER
	public static SkuMasterDataGetterSetter SkuMasterDataXMLHandler(
			XmlPullParser xpp, int eventType) {
		SkuMasterDataGetterSetter jcpGetterSetter = new SkuMasterDataGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("SKU_ID")) {
						jcpGetterSetter.setSku_id(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_NAME")) {
						jcpGetterSetter.setSku_name(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_ID")) {
						jcpGetterSetter.setBrand_id(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_SEQUENCE")) {
						jcpGetterSetter.setSkuseq(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// COMPANY MASTER
	public static CompanyMasterGetterSetter CompanyMasterXMLHandler(
			XmlPullParser xpp, int eventType) {
		CompanyMasterGetterSetter jcpGetterSetter = new CompanyMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("COMPANY_ID")) {
						jcpGetterSetter.setCompanyid(xpp.nextText());
					}
					if (xpp.getName().equals("COMPANY")) {
						jcpGetterSetter.setCompany(xpp.nextText());
					}
					if (xpp.getName().equals("ISCOMPETITOR")) {
						jcpGetterSetter.setCompetitor(xpp.nextText());
					}
					if (xpp.getName().equals("NONKEYCOMPETITOR")) {
						jcpGetterSetter.setNonkeycompetitor(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// gEO TAG
	public static GeoTagCityGetterSetter GeoTagCityXMLHandler(
			XmlPullParser xpp, int eventType) {
		GeoTagCityGetterSetter jcpGetterSetter = new GeoTagCityGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("CITY_ID")) {
						jcpGetterSetter.setCityid(xpp.nextText());
					}
					if (xpp.getName().equals("CITY_NAME")) {
						jcpGetterSetter.setCity(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// Geo Tags
	public static GeoTagGetterSetter GeoTagXMLHandler(XmlPullParser xpp,
			int eventType) {
		GeoTagGetterSetter jcpGetterSetter = new GeoTagGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_ID")) {
						jcpGetterSetter.setStoreid(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_NAME")) {
						jcpGetterSetter.setStorename(xpp.nextText());
					}
					if (xpp.getName().equals("ADDRESS")) {
						jcpGetterSetter.setAddress(xpp.nextText());
					}
					if (xpp.getName().equals("CITY_ID")) {
						jcpGetterSetter.setCityid(xpp.nextText());
					}
					if (xpp.getName().equals("STORETYPE_ID")) {
						jcpGetterSetter.setStoretype(xpp.nextText());
					}
					if (xpp.getName().equals("LOGITUDE")) {
						jcpGetterSetter.setLongitude(xpp.nextText());
					}
					if (xpp.getName().equals("LATITUDE")) {
						jcpGetterSetter.setLatitude(xpp.nextText());
					}
					if (xpp.getName().equals("STATUS")) {
						jcpGetterSetter.setStatus(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// Non Stock Reason
	public static NonSkuGetterSetter NonSkuXMLHandler(XmlPullParser xpp,
			int eventType) {
		NonSkuGetterSetter jcpGetterSetter = new NonSkuGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STOCK_REASON_ID")) {
						jcpGetterSetter.setStockreasonid(xpp.nextText());
					}
					if (xpp.getName().equals("STOCK_REASON")) {
						jcpGetterSetter.setStockreason(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// SKU FOCUS
	public static SkufocusGetterSetter SkufocusXMLHandler(XmlPullParser xpp,
			int eventType) {
		SkufocusGetterSetter jcpGetterSetter = new SkufocusGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_ID")) {
						jcpGetterSetter.setSTORE_ID(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_ID")) {
						jcpGetterSetter.setSKU_ID(xpp.nextText());
					}
					if (xpp.getName().equals("FOCUS")) {
						jcpGetterSetter.setFOCUS(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// MAPPING COMPETITOR
	public static CompetitorGetterSetter CompetitorXMLHandler(
			XmlPullParser xpp, int eventType) {
		CompetitorGetterSetter jcpGetterSetter = new CompetitorGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("BRAND_ID")) {
						jcpGetterSetter.setBrandid(xpp.nextText());
					}
					if (xpp.getName().equals("COMP_BRAND_ID")) {
						jcpGetterSetter.setCompetitor_brandid(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// SKUTAREGT
	public static SkuTargetGetterSetter SkuTargetXMLHandler(XmlPullParser xpp,
			int eventType) {
		SkuTargetGetterSetter jcpGetterSetter = new SkuTargetGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_ID")) {
						jcpGetterSetter.setSTORE_ID(xpp.nextText());
					}
					if (xpp.getName().equals("POSM_ID")) {
						jcpGetterSetter.setPosm_id(xpp.nextText());
					}

					if (xpp.getName().equals("TARGET_QTY")) {
						jcpGetterSetter.setTarget(xpp.nextText());
					}

				}
				xpp.next();
			}

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	// promotion
	public static PromotionGetterSetter PromotionXMLHandler(XmlPullParser xpp,
			int eventType) {
		PromotionGetterSetter jcpGetterSetter = new PromotionGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_ID")) {
						jcpGetterSetter.setStoreid(xpp.nextText());
					}
					if (xpp.getName().equals("PROMO_ID")) {
						jcpGetterSetter.setPROMO_ID(xpp.nextText());
					}
					if (xpp.getName().equals("PROMOTYPE_ID")) {
						jcpGetterSetter.setPROMOTYPE_ID(xpp.nextText());
					}
					if (xpp.getName().equals("PROMO")) {
						jcpGetterSetter.setPROMO(xpp.nextText());
					}
					if (xpp.getName().equals("PROMO_TYPE")) {
						jcpGetterSetter.setPROMO_TYPE(xpp.nextText());
					}
					if (xpp.getName().equals("VERTICAL_ID")) {
						jcpGetterSetter.setVertical_id(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	
	// target brand wise download
	
	
		public static TargetBrandWiseGetterSetter getTargetBrandWise(XmlPullParser xpp, int eventType) {
			TargetBrandWiseGetterSetter jcpGetterSetter = new TargetBrandWiseGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						if (xpp.getName().equals("META_DATA")) {
							jcpGetterSetter.setMeta_data(xpp.nextText());
						}
						
						if (xpp.getName().equals("VERTICAL_ID")) {
							jcpGetterSetter.setVerticalId(xpp.nextText());
						}
						if (xpp.getName().equals("BRAND_ID")) {
							jcpGetterSetter.setBrandid(xpp.nextText());
						}
						
						if (xpp.getName().equals("TARGET")) {
							jcpGetterSetter.setTarget(xpp.nextText());
						}
						
					

					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jcpGetterSetter;
		}
		
		
		//parse kpi report data
		
		
		public static KpiReportGetterSetter KPIREPORT_DATA(XmlPullParser xpp,
				int eventType) {
			KpiReportGetterSetter jcpGetterSetter = new KpiReportGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						if (xpp.getName().equals("STORE_ID")) {
							jcpGetterSetter.setStoreid(xpp.nextText());
						}
						if (xpp.getName().equals("VERTICAL")) {
							jcpGetterSetter.setVertical_Type(xpp.nextText());
						}
						if (xpp.getName().equals("KPI")) {
							jcpGetterSetter.setKPI(xpp.nextText());
						}
						
						if (xpp.getName().equals("KPI_VALUE")) {
							jcpGetterSetter.setKpi_Value(xpp.nextText());
						}
					
						if (xpp.getName().equals("SRNO")) {
							jcpGetterSetter.setSR_NO(xpp.nextText());
						}
						
						
/*						<STORE_KPI>

						<STORE_ID>187</STORE_ID>
						<VERTICAL>Food</VERTICAL>
						<KPI>SOS</KPI>
						<KPI_VALUE>87</KPI_VALUE>
						</STORE_KPI>*/
						
						
					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jcpGetterSetter;
		}
		
		
		
		
		
		
		
		
	
		// parse kpi target
		public static TargetKPIGetterSetter KPITARGET(XmlPullParser xpp,
				int eventType) {
			TargetKPIGetterSetter jcpGetterSetter = new TargetKPIGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						if (xpp.getName().equals("KPI")) {
							jcpGetterSetter.setkpi(xpp.nextText());
						}
						if (xpp.getName().equals("FOOD")) {
							jcpGetterSetter.setfood(xpp.nextText());
						}
						if (xpp.getName().equals("BEV")) {
							jcpGetterSetter.setbev(xpp.nextText());
						}
					
					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jcpGetterSetter;
		}

}
