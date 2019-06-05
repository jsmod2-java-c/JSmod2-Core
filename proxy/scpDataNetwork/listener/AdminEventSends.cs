using Smod2.EventHandlers;
using Smod2.Events;

/**
 * @author MagicLu550 #(code) jsmod2
 */

namespace scpDataNetwork.listener
{
    public class AdminQueryHandler : IEventHandlerAdminQuery
    {
        private const string ADMIN_QUERY = "event.AdminQuery";
        
        public void OnAdminQuery(AdminQueryEvent ev)
        {
            
        }
    }
}