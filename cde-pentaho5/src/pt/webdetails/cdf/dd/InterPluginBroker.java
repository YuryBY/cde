package pt.webdetails.cdf.dd;

import org.apache.commons.lang.StringUtils;

import pt.webdetails.cpf.PluginEnvironment;
import pt.webdetails.cpf.plugin.CorePlugin;
import pt.webdetails.cpf.plugincall.api.IPluginCall;
import pt.webdetails.cpf.plugincall.base.CallParameters;

/**
 * at least put cdf stuff here
 */
public class InterPluginBroker {

  public static final String DATA_SOURCE_DEFINITION_METHOD_NAME = "listDataAccessTypes";

  /**
   *
   * @param dashboard
   * @param type
   * @param debug
   * @param absolute
   * @param absRoot
   * @param scheme
   * @return
   * @throws Exception
   */
  public static String getCdfIncludes( String dashboard, String type, boolean debug, boolean absolute,
    String absRoot, String scheme ) throws Exception {

    CallParameters params = new CallParameters();
    params.put( "dashboardContent", dashboard );
    params.put( "debug", debug );
    if ( type != null ) { params.put( "dashboardType", type ); }
    if ( !StringUtils.isEmpty( absRoot ) ) { params.put( "root", absRoot ); }
    if ( !StringUtils.isEmpty( scheme ) ) { params.put( "scheme", scheme ); }
    params.put( "absolute", absolute );

    //TODO: instantiate directly
    IPluginCall pluginCall = PluginEnvironment.env().getPluginCall( CorePlugin.CDF.getId(), "xcdf", "getHeaders" );
    return pluginCall.call( params.getParameters() );
  }

  /**
   *
   * @param plugin
   * @param service
   * @param method
   * @param forceRefresh
   * @return
   * @throws Exception
   */
  public static String getDataSourceDefinitions( String plugin, String service, String method, boolean forceRefresh )
    throws Exception {
    CallParameters params = new CallParameters();
    params.put( "refreshCache", forceRefresh );

    IPluginCall pluginCall = PluginEnvironment.env().getPluginCall( plugin, null, method );
    return pluginCall.call( params.getParameters() );
  }

  /**
   *
   * @param dashboard
   * @param action
   * @param viewId
   * @return
   * @throws Exception
   */
  public static String getCdfContext( String dashboard, String action, String viewId ) throws Exception {
    CallParameters params = new CallParameters();
    params.put( "path", dashboard );
    params.put( "action", action );
    params.put( "viewId", viewId );

    IPluginCall pluginCall = PluginEnvironment.env().getPluginCall( CorePlugin.CDF.getId(), "xcdf", "getContext" );
    return pluginCall.call( params.getParameters() );
  }

  /**
   *
   * @param dashboard
   * @return
   * @throws Exception
   */
  public static String getCdfRequireContext( String dashboard ) throws Exception {
    CallParameters params = new CallParameters();
    params.put( "path", dashboard );

    IPluginCall pluginCall = PluginEnvironment.env().getPluginCall( CorePlugin.CDF.getId(), "context", "get" );
    return pluginCall.call( params.getParameters() );
  }
}
