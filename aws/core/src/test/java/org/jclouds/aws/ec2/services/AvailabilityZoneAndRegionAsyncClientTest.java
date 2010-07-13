/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */
package org.jclouds.aws.ec2.services;

import static org.jclouds.aws.ec2.options.DescribeAvailabilityZonesOptions.Builder.availabilityZones;
import static org.jclouds.aws.ec2.options.DescribeRegionsOptions.Builder.regions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.jclouds.aws.domain.Region;
import org.jclouds.aws.ec2.domain.AvailabilityZone;
import org.jclouds.aws.ec2.options.DescribeAvailabilityZonesOptions;
import org.jclouds.aws.ec2.options.DescribeRegionsOptions;
import org.jclouds.aws.ec2.xml.DescribeAvailabilityZonesResponseHandler;
import org.jclouds.aws.ec2.xml.DescribeRegionsResponseHandler;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.functions.ParseSax;
import org.jclouds.rest.internal.RestAnnotationProcessor;
import org.testng.annotations.Test;

import com.google.inject.TypeLiteral;

/**
 * Tests behavior of {@code AvailabilityZoneAndRegionAsyncClient}
 * 
 * @author Adrian Cole
 */
@Test(groups = "unit", testName = "ec2.AvailabilityZoneAndRegionAsyncClientTest")
public class AvailabilityZoneAndRegionAsyncClientTest extends
         BaseEC2AsyncClientTest<AvailabilityZoneAndRegionAsyncClient> {

   public void testDescribeAvailabilityZones() throws SecurityException, NoSuchMethodException,
            IOException {
      Method method = AvailabilityZoneAndRegionAsyncClient.class.getMethod(
               "describeAvailabilityZonesInRegion", String.class, Array.newInstance(
                        DescribeAvailabilityZonesOptions.class, 0).getClass());
      HttpRequest request = processor.createRequest(method, Region.US_WEST_1);

      assertRequestLineEquals(request, "POST https://ec2.us-west-1.amazonaws.com/ HTTP/1.1");
      assertNonPayloadHeadersEqual(request, "Host: ec2.us-west-1.amazonaws.com\n");
      assertPayloadEquals(request, "Version=2010-06-15&Action=DescribeAvailabilityZones",
               "application/x-www-form-urlencoded", false);

      assertResponseParserClassEquals(method, request, ParseSax.class);
      assertSaxResponseParserClassEquals(method, DescribeAvailabilityZonesResponseHandler.class);
      assertExceptionParserClassEquals(method, null);

      checkFilters(request);
   }

   public void testDescribeAvailabilityZonesOptions() throws SecurityException,
            NoSuchMethodException, IOException {
      Method method = AvailabilityZoneAndRegionAsyncClient.class.getMethod(
               "describeAvailabilityZonesInRegion", String.class, Array.newInstance(
                        DescribeAvailabilityZonesOptions.class, 0).getClass());
      HttpRequest request = processor.createRequest(method, Region.US_EAST_1, availabilityZones(
               AvailabilityZone.US_EAST_1A, AvailabilityZone.US_EAST_1B));

      assertRequestLineEquals(request, "POST https://ec2.us-east-1.amazonaws.com/ HTTP/1.1");
      assertNonPayloadHeadersEqual(request, "Host: ec2.us-east-1.amazonaws.com\n");
      assertPayloadEquals(
               request,
               "Version=2010-06-15&Action=DescribeAvailabilityZones&ZoneName.1=us-east-1a&ZoneName.2=us-east-1b",
               "application/x-www-form-urlencoded", false);

      assertResponseParserClassEquals(method, request, ParseSax.class);
      assertSaxResponseParserClassEquals(method, DescribeAvailabilityZonesResponseHandler.class);
      assertExceptionParserClassEquals(method, null);

      checkFilters(request);
   }

   public void testDescribeRegions() throws SecurityException, NoSuchMethodException, IOException {
      Method method = AvailabilityZoneAndRegionAsyncClient.class.getMethod("describeRegions", Array
               .newInstance(DescribeRegionsOptions.class, 0).getClass());
      HttpRequest request = processor.createRequest(method);

      assertRequestLineEquals(request, "POST https://ec2.us-east-1.amazonaws.com/ HTTP/1.1");
      assertNonPayloadHeadersEqual(request, "Host: ec2.us-east-1.amazonaws.com\n");
      assertPayloadEquals(request, "Version=2010-06-15&Action=DescribeRegions",
               "application/x-www-form-urlencoded", false);

      assertResponseParserClassEquals(method, request, ParseSax.class);
      assertSaxResponseParserClassEquals(method, DescribeRegionsResponseHandler.class);
      assertExceptionParserClassEquals(method, null);

      checkFilters(request);
   }

   public void testDescribeRegionsOptions() throws SecurityException, NoSuchMethodException,
            IOException {
      Method method = AvailabilityZoneAndRegionAsyncClient.class.getMethod("describeRegions", Array
               .newInstance(DescribeRegionsOptions.class, 0).getClass());
      HttpRequest request = processor.createRequest(method, regions(Region.US_EAST_1,
               Region.US_WEST_1));

      assertRequestLineEquals(request, "POST https://ec2.us-east-1.amazonaws.com/ HTTP/1.1");
      assertNonPayloadHeadersEqual(request, "Host: ec2.us-east-1.amazonaws.com\n");
      assertPayloadEquals(
               request,
               "Version=2010-06-15&Action=DescribeRegions&RegionName.1=us-east-1&RegionName.2=us-west-1",
               "application/x-www-form-urlencoded", false);

      assertResponseParserClassEquals(method, request, ParseSax.class);
      assertSaxResponseParserClassEquals(method, DescribeRegionsResponseHandler.class);
      assertExceptionParserClassEquals(method, null);

      checkFilters(request);
   }

   @Override
   protected TypeLiteral<RestAnnotationProcessor<AvailabilityZoneAndRegionAsyncClient>> createTypeLiteral() {
      return new TypeLiteral<RestAnnotationProcessor<AvailabilityZoneAndRegionAsyncClient>>() {
      };
   }

}
