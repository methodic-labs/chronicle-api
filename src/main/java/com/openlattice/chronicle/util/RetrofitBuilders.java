/*
 * Copyright (C) 2017. OpenLattice, Inc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * You can contact the owner of the copyright at support@openlattice.com
 *
 */

package com.openlattice.chronicle.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.openlattice.chronicle.serialization.ChronicleCallAdapterFactory;
import com.openlattice.chronicle.serialization.ChronicleJacksonConverterFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
public class RetrofitBuilders {
    public static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule( new GuavaModule() );
        mapper.registerModule( new JodaModule() );
        mapper.registerModule( new JavaTimeModule() );
        mapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
//        mapper.registerModule( new AfterburnerModule() );
    }

    public static OkHttpClient.Builder okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout( 0, TimeUnit.MILLISECONDS )
                .writeTimeout( 0, TimeUnit.MILLISECONDS )
                .connectTimeout( 0, TimeUnit.MILLISECONDS );
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static final Retrofit.Builder decorateWithRhizomeFactories( Retrofit.Builder builder ) {
        return builder
                .addConverterFactory( new ChronicleJacksonConverterFactory( mapper ) )
                .addCallAdapterFactory( new ChronicleCallAdapterFactory() );
    }

    public static final Retrofit.Builder createBaseChronicleRetrofitBuilder( String baseUrl, OkHttpClient httpClient ) {
        return new Retrofit.Builder().baseUrl( baseUrl ).client( httpClient );
    }
}
