var skillsdata;

skillsdata = {
  "Dependências": {
    "application": {
    	"DialogActionListener": [1,1,1],
    	"DialogNavigationHandler": [3,3,3],
    	"DialogViewHandler": [3,3,3],
    	"PrimeResource": [10, 0,0],
    	"PrimeResourceHandler": [4,0,0],
    	"exceptionhandler": {
    		"ExceptionInfo": [14,14,14],
    		"PrimeExceptionHandler": [10,10,11],
    		"PrimeExceptionHandlerELResolver": [6,6,6],
    		"PrimeExceptionHandlerFactory": [3,3,3]
    	},
    	"resource": {
    		"BaseDynamicContentHandler": [0, 1,1],
    		"PrimeResource": [0,10,10],
    		"PrimeResourceHandler": [0,6,6],
    		"QRCodeHandler": [0,1,1],
    		"StreamedContentHandler": [0,1,1],
    		"barcode": {
    			"BarcodeHandler": [0,1,1],
    			"CodabarGenerator": [0,1,1],
    			"Code128Generator": [0,1,1],
    			"Code39Generator": [0,1,1],
    			"DataMatrixGenerator": [0,1,1],
    			"EAN13Generator": [0,1,1],
    			"EAN8Generator": [0,1,1],
    			"Int2of5Generator": [0,1,1],
    			"PDF417Generator": [0,1,1],
    			"PostnetGenerator": [0,1,1],
    			"UPCAGenerator": [0,1,1],
    			"UPCEGenerator": [0,1,1]
    		}
    	}
    },
    "behavior": {
    	"ajax": {
    		"AjaxBehavior": [39,41,43],
    		"AjaxBehaviorHandler": [2,2,2],
    		"AjaxBehaviorListenerImpl": [3,3,3],
    		"AjaxBehaviorRenderer": [3,3,3]
    	},
    	"base": {
    		"AbstractBehavior": [13,13,13],
    		"AbstractBehaviorHandler": [10,10,10]
    	},
    	"confirm": {
    		"ConfirmBehavior": [7,7,7]
    	},
    	"printer": {
    		"PrinterBehavior": [3,3,3]	
    	},
    	"validate": {
    		"ClientValidator": [5,5,5]	
    	}
    },
    "cache": {
    	"DefaultCacheProvider": [5,5,5],
    	"EHCacheProvider": [7,7,7],
    	"HazelcastCacheProvider": [7,7,7]
    },
    "component": {
    	"accordionpanel": {
    		"AccordionPanel": [29,29,29],
    		"AccordionPanelHandler": [1,1,1],
    		"AccordionPanelRenderer": [9,9,9]
    	},
    	"ajaxexceptionhandler": {
    		"AjaxExceptionHandler": [7,7,7],
    		"AjaxExceptionHandlerVisitCallback": [2,2,2]
    	},
    	"ajaxstatus": {
    		"AjaxStatus": [16,16,16],
    		"AjaxStatusRenderer": [4,4,4]
    	},
    	"api": {
    		"DynamicColumn": [40,41,46],
    		"RepeatStatus": [8,8,8],
    		"SavedState": [10,10,10],
    		"UIData": [58,58,79],
    		"UITabPanel": [59,59,59],
    		"UITree": [49,51,59],
    		"WrapperEvent": [6,6,6]
    	},
    	"autocomplete": {
    		"AutoComplete": [58,62,75],
    		"AutoCompleteHandler": [1,1,1],
    		"AutoCompleteRenderer": [20,20,20]
    	},
    	"barcode": {
    		"Barcode": [0,9,9],
    		"BarcodeRenderer": [0,1,1]
    	},
    	"blockui": {
    		"BlockUI": [12,12,12],
    		"BlockUIRenderer": [5,5,5]
    	},
    	"breadcrumb": {
    		"BreadCrumb": [12,12,10],
        	"BreadCrumbRenderer": [5,5,5],
    	},
    	"button": {
    		"Button": [25,25,25],
    		"ButtonRenderer": [4,4,4]
    	},
    	"cache": {
    		"UICache": [7,7,14],
    		"UICacheRenderer": [2,2,2]
    	},
    	"calendar": {
    		"Calendar": [82,84,85],
    		"CalendarRenderer": [6,6,6],
    		"CalendarUtils": [4,4,4],
    		"converter": {
    			"DatePatternConverter": [0,1,1],
        		"TimePatternConverter": [0,1,1]
    		}
    	},
    	"captcha": {
    		"Captcha": [13,13,13],
        	"CaptchaRenderer": [3,3,3],
        	"Verification": [2,2,2]
    	},
    	"carousel": {
    		"Carousel": [38,38,38],
    		"CarouselRenderer": [12,12,12]
    	},
    	"celleditor": {
    		"CellEditor": [5,5,5],
    		"CellEditorRenderer": [3,3,3]
    	},
    	"chart": {
    		"BaseChartRenderer": [4,0,0],
    		"CartesianChart": [1,0,0],
    		"Chart": [15,15,17],
    		"ChartRenderer": [4,4,4],
    		"UIChart": [31,0,0],
    		"bar": {
    			"BarChart": [26,0,0],
        		"BarChartRenderer": [4,0,0]
    		},
    		"bubble": {
    			"BubbleChart": [19,0,0],
    			"BubbleChartRenderer": [4,0,0]
    		},
    		"donut": {
    			"DonutChart": [15,0,0],
        		"DonutChartRenderer": [4,0,0]
    		},
    		"line": {
    			"LineChart": [28,0,0],
        		"LineChartRenderer": [4,0,0]
    		},
    		"metergauge": {
    			"MeterGaugeChart": [19,0,0],
        		"MeterGaugeChartRenderer": [5,0,0]
    		},
    		"ohlc": {
    			"OhlcChart": [17,0,0],
        		"OhlcChartRenderer": [4,0,0]
    		},
    		"pie": {
    			"PieChart": [14,0,0],
        		"PieChartRenderer": [4,0,0]
    		},
    		"": {
    			"BarRenderer": [2,2,2],
        		"BasePlotRenderer": [3,3,3],
        		"BubbleRenderer": [2,2,2],
        		"CartesianPlotRenderer": [2,2,2],
        		"DonutRenderer": [2,2,2],
        		"LineRenderer": [2,2,2],
        		"MeterGaugeRenderer": [3,3,3],
        		"OhlcRenderer": [2,2,2],
        		"PieRenderer": [2,2,2]
    		}
    	}
    }
    
  }
};