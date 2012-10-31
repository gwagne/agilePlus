package opensource.pc.gw.services;

import opensource.pc.gw.entities.User;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author olemerdy
 * @since 26/10/12
 */
public class TapTrainRealm extends AuthorizingRealm {

    private Session hbSession;

    public TapTrainRealm(@Inject Session hbSession) {
        this.hbSession = hbSession;
        super.setCacheManager(new MemoryConstrainedCacheManager());
        super.setName("TapTrainRealm");
        super.setAuthenticationTokenClass(UsernamePasswordToken.class);
        super.setCredentialsMatcher(new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
                UsernamePasswordToken upToken = (UsernamePasswordToken) token;
                String login = upToken.getUsername();
                String password = new String(upToken.getPassword());
                return login.equals(password);
            }
        });
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addObjectPermission(new AllPermission());
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String login = ((UsernamePasswordToken) token).getUsername();
        User user = (User) hbSession.createCriteria(User.class).add(Restrictions.eq(User.Properties.email, login)).uniqueResult();
        if (user == null) {
            throw new AccountException("Unknown user " + upToken.getUsername());
        }
        return new SimpleAuthenticationInfo(upToken.getUsername(), upToken.getPassword(), "TapTrainRealm");
    }

}
