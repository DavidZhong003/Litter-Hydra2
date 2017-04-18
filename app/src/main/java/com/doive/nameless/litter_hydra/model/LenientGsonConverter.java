package com.doive.nameless.litter_hydra.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LenientGsonConverter
        /*extends GsonConverter*/ {
    //        private Gson mGson;
    //
    //        public LenientGsonConverter(Gson gson) {
    //            super(gson);
    //            mGson = gson;
    //        }
    //
    //        public LenientGsonConverter(Gson gson, String charset) {
    //            super(gson, charset);
    //            mGson = gson;
    //        }
    //
    //        @Override
    //        public Object fromBody(TypedInput body, Type type) throws ConversionException {
    //            boolean willCloseStream = false; // try to close the stream, if there is no exception thrown using tolerant  JsonReader
    //            try {
    //                JsonReader jsonReader = new JsonReader(new InputStreamReader(body.in()));
    //                jsonReader.setLenient(true);
    //                Object o = mGson.fromJson(jsonReader,type);
    //                willCloseStream = true;
    //                return o;
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }finally {
    //                if(willCloseStream) {
    //                    closeStream(body);
    //                }
    //            }
    //
    //            return super.fromBody(body, type);
    //        }
    //
    //        private void closeStream(TypedInput body){
    //            try {
    //                InputStream in = body.in();
    //                in.close();
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }
}