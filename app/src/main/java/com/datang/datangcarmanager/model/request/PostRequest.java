package com.datang.datangcarmanager.model.request;

import com.datang.datangcarmanager.model.request.Params;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.model
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午9:36
 * @change
 * @chang time
 * @class describe
 */
public class PostRequest {

    /**
     * cmd : corpDeptVehicleListV2
     * auth : {"password":"000000","mapType":"google","appName":"xfinder4company","userName":"demo@sme"}
     * params : {"isBind":"1","vehicleIds":[],"hiddenNoContractVec":"1","deptIds":[],"corpId":"12120710341890007"}
     */

    private String cmd;
    private AuthBean auth;
    private Params params;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public AuthBean getAuth() {
        return auth;
    }

    public void setAuth(AuthBean auth) {
        this.auth = auth;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public static class AuthBean {
        /**
         * password : 000000
         * mapType : google
         * appName : xfinder4company
         * userName : demo@sme
         */

        private String password;
        private String mapType;
        private String appName;
        private String userName;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMapType() {
            return mapType;
        }

        public void setMapType(String mapType) {
            this.mapType = mapType;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

}
