import React from 'react';
import { StyleSheet,
  Text,
  View,
  Button,
  TextInput,
  Image,
  AppRegistry,
  NativeModules } from 'react-native';
import JasonetteAndroid from './JasonetteAndroid';

export default class jasonetto extends React.Component {

  constructor(props) {
    super(props);
    this.state = { text: '' };
  }

  render() {
    return (
        <Image
            source={ require('./background_landscape.jpg') }
            style={ styles.container }
        >

          <TextInput
            style={styles.link}
            placeholder="URL"
            onChangeText={(txt) => this.setState({ text: txt })}
            keyboardType='url'
          />

          <View style={styles.buttonContainer}>
            <Button
              onPress={() => JasonetteAndroid.show(this.state.text)}
              title=" GO "
              color="#086A87"
            />
          </View>

          <View style={styles.footer}>
              <Text style={{ fontSize: 12, color: '#fff' }}>   © eteration   </Text>
              <Text style={{ fontSize: 12, color: '#fff' }}>   Powered by ReactNative   </Text>
          </View>

        </Image>
    );
  }

}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: null,
    height: null,
  },
  link: {
    marginTop: '10%',
    height: 50,
    justifyContent: 'flex-start',
    alignSelf: 'stretch',
    color: 'white',
    fontSize: 20,
  },
  buttonContainer: {
    margin: 30,
    flexDirection: 'row',
    justifyContent: 'center',
  },
  footer: {
    marginTop: '100%',
    flexDirection: 'column',
    alignItems: 'center',
  },
});

AppRegistry.registerComponent('jasonetto', () => jasonetto);
