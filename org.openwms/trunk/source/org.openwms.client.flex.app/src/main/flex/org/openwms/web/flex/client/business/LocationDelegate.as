/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.web.flex.client.business
{
    import com.adobe.cairngorm.business.ServiceLocator;

    import mx.rpc.AsyncToken;
    import mx.rpc.IResponder;

    /**
     * A LocationDelegate.
     *
     * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
     * @version $Revision: 235 $
     */
    public class LocationDelegate
    {
        private var responder:IResponder;
        private var service:Object;

        public function LocationDelegate(responder:IResponder):void
        {
            this.responder = responder;
            this.service = ServiceLocator.getInstance().getRemoteObject("locationService");
        }

        public function getLocations():void
        {
            var call:AsyncToken = service.findAll();
            call.addResponder(responder);
        }

    }
}